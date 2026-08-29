import dash
from dash import dcc, html, Input, Output
import plotly.graph_objects as go
import numpy as np

# ==========================================
# 1. CORE MATH FUNCTIONS (R^2 -> R^3 Mapping)
# ==========================================
def r(u, v):
    """Maps a 2D parameter point (u, v) to a 3D position (x, y, z)."""
    return np.array([u, v, u**2 + v**2])

def r_u(u, v):
    """Partial derivative w.r.t u (Tangent vector along u-direction)."""
    return np.array([1, 0, 2*u])

def r_v(u, v):
    """Partial derivative w.r.t v (Tangent vector along v-direction)."""
    return np.array([0, 1, 2*v])

# ==========================================
# 2. INITIALIZE DASH APP
# ==========================================
app = dash.Dash(__name__)

# Build the layout of our interactive dashboard with English UI
app.layout = html.Div([
    html.H2("Local Linearization: Mapping Flat Domain to Curved Surface", 
            style={'textAlign': 'center', 'fontFamily': 'sans-serif'}),
    
    # SLIDER CONTAINER
    html.Div([
        html.Label("Grid Resolution (N x N):", style={'fontWeight': 'bold', 'fontFamily': 'sans-serif'}),
        dcc.Slider(
            id='resolution-slider',
            min=4, max=24, step=2, value=10,
            marks={i: str(i) for i in range(4, 26, 2)},
            tooltip={"placement": "bottom", "always_visible": True}
        )
    ], style={'width': '50%', 'margin': '0 auto', 'paddingBottom': '20px'}),
    
    # GRAPHS CONTAINER (Side-by-side layout)
    html.Div([
        # Left Panel: 2D Grid
        html.Div([
            dcc.Graph(id='plot-2d', clear_on_unhover=False, style={'height': '70vh'})
        ], style={'width': '40%', 'display': 'inline-block', 'verticalAlign': 'top'}),
        
        # Right Panel: 3D Surface
        html.Div([
            dcc.Graph(id='plot-3d', style={'height': '70vh'})
        ], style={'width': '60%', 'display': 'inline-block', 'verticalAlign': 'top'})
    ])
])

# ==========================================
# 3. INTERACTIVITY CALLBACK
# ==========================================
@app.callback(
    [Output('plot-2d', 'figure'), Output('plot-3d', 'figure')],
    [Input('resolution-slider', 'value'), Input('plot-2d', 'hoverData')]
)
def update_dashboard(N, hoverData):
    # Determine the step size based on grid resolution
    du = 4.0 / N
    dv = 4.0 / N
    
    # Edge points for the grid (from -2 to 2)
    u_edges = np.linspace(-2, 2, N+1)
    v_edges = np.linspace(-2, 2, N+1)
    
    # Determine which cell the user is hovering over
    if hoverData is not None:
        hover_x = hoverData['points'][0]['x']
        hover_y = hoverData['points'][0]['y']
        u0 = np.floor((hover_x + 2) / du) * du - 2
        v0 = np.floor((hover_y + 2) / dv) * dv - 2
        u0 = min(max(u0, -2.0), 2.0 - du)
        v0 = min(max(v0, -2.0), 2.0 - dv)
    else:
        u0, v0 = 0.0, 0.0

    # ====================================================
    # BUILD 2D FIGURE (uv-plane)
    # ====================================================
    fig2d = go.Figure()
    
    # Draw grid lines
    x_lines, y_lines = [], []
    for edge in u_edges:
        x_lines.extend([edge, edge, None])
        y_lines.extend([-2, 2, None])
    for edge in v_edges:
        x_lines.extend([-2, 2, None])
        y_lines.extend([edge, edge, None])
    
    fig2d.add_trace(go.Scatter(x=x_lines, y=y_lines, mode='lines', 
                               line=dict(color='lightgray'), hoverinfo='skip', showlegend=False))
    
    # Highlight the active patch R_ij
    fig2d.add_trace(go.Scatter(
        x=[u0, u0+du, u0+du, u0, u0],
        y=[v0, v0, v0+dv, v0+dv, v0],
        fill="toself", fillcolor="rgba(255, 0, 0, 0.5)", 
        line=dict(color="red", width=2),
        name="Active Patch R_ij"
    ))
    
    # Invisible scatter targets for smooth hovering
    centers_u = u_edges[:-1] + du/2
    centers_v = v_edges[:-1] + dv/2
    CU, CV = np.meshgrid(centers_u, centers_v)
    fig2d.add_trace(go.Scatter(
        x=CU.flatten(), y=CV.flatten(), mode='markers',
        marker=dict(size=20, opacity=0),
        hoverinfo='none', showlegend=False
    ))
    
    fig2d.update_layout(
        title="2D Parameter Domain D",
        xaxis=dict(title='u axis', range=[-2.1, 2.1]),
        yaxis=dict(title='v axis', range=[-2.1, 2.1], scaleanchor="x", scaleratio=1),
        uirevision='constant',
        margin=dict(l=40, r=40, t=40, b=40)
    )

    # ====================================================
    # BUILD 3D FIGURE (xyz-space)
    # ====================================================
    fig3d = go.Figure()
    
    # Main Curved Surface S with visible Grid Curves (Contours)
    U, V = np.meshgrid(u_edges, v_edges)
    Z = U**2 + V**2
    fig3d.add_trace(go.Surface(
        x=U, y=V, z=Z, 
        colorscale='Blues', opacity=0.7, showscale=False,
        name='Curved Surface S',
        # Adding the structural grid lines on the 3D surface
        contours=dict(
            x=dict(show=True, color='rgba(0, 0, 0, 0.2)', width=1),
            y=dict(show=True, color='rgba(0, 0, 0, 0.2)', width=1)
        )
    ))
    
    # Compute local tangent vectors
    P0 = r(u0, v0)
    vec_u = r_u(u0, v0) * du
    vec_v = r_v(u0, v0) * dv
    
    # Create the Local Tangent Parallelogram
    s_arr = np.array([0, 1])
    t_arr = np.array([0, 1])
    S_grid, T_grid = np.meshgrid(s_arr, t_arr)
    
    Patch_X = P0[0] + S_grid * vec_u[0] + T_grid * vec_v[0]
    Patch_Y = P0[1] + S_grid * vec_u[1] + T_grid * vec_v[1]
    Patch_Z = P0[2] + S_grid * vec_u[2] + T_grid * vec_v[2]
    
    fig3d.add_trace(go.Surface(
        x=Patch_X, y=Patch_Y, z=Patch_Z,
        colorscale='Reds', opacity=0.9, showscale=False,
        name='Tangent Parallelogram S_ij'
    ))
    
    # Draw Tangent Vectors
    fig3d.add_trace(go.Scatter3d(
        x=[P0[0], P0[0] + vec_u[0]], y=[P0[1], P0[1] + vec_u[1]], z=[P0[2], P0[2] + vec_u[2]],
        mode='lines', line=dict(color='green', width=6), name='Vector r_u * du'
    ))
    fig3d.add_trace(go.Scatter3d(
        x=[P0[0], P0[0] + vec_v[0]], y=[P0[1], P0[1] + vec_v[1]], z=[P0[2], P0[2] + vec_v[2]],
        mode='lines', line=dict(color='purple', width=6), name='Vector r_v * dv'
    ))
    
    # Mark the origin node P0
    fig3d.add_trace(go.Scatter3d(
        x=[P0[0]], y=[P0[1]], z=[P0[2]],
        mode='markers', marker=dict(color='black', size=5), name='Node Point P0'
    ))
    
    fig3d.update_layout(
        title="3D Mapped Space",
        scene=dict(
            xaxis_title='X Axis', yaxis_title='Y Axis', zaxis_title='Z Axis',
            aspectmode='data',
            xaxis=dict(range=[-2.5, 2.5]),
            yaxis=dict(range=[-2.5, 2.5]),
            zaxis=dict(range=[0, 8])
        ),
        uirevision='constant',
        margin=dict(l=0, r=0, t=40, b=0)
    )

    return fig2d, fig3d

# ==========================================
# 4. RUN THE SERVER
# ==========================================
if __name__ == '__main__':
    # Chủ động in thông báo ra terminal để biết code đã chạy đến đây
    print("server started successfully")
    print("open your web browser and navigate to http://127.0.0.1:8050/ to view the interactive visualization.")
    print("Press Ctrl+C on the terminal to stop the server when you're done.\n")
    
    # Start the server (the code will be blocked here)
    app.run(debug=True)