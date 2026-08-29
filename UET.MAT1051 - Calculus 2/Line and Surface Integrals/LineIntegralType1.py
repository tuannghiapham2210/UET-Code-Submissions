import numpy as np
import matplotlib.pyplot as plt
from matplotlib.widgets import Slider
from mpl_toolkits.mplot3d.art3d import Poly3DCollection

# ==========================================
# 1. MATHEMATICAL MODEL SETUP 
# Based on Example 2.1 (Slide 16): 
# C is the upper half of the unit circle.
# f(x, y) = 2 + x^2 * y
# ==========================================

def x_t(t): 
    return np.cos(t)

def y_t(t): 
    return np.sin(t)

def f(x, y): 
    return 2 + (x**2) * y

# The exact analytical area calculated via integration
EXACT_AREA = 2 * np.pi + 2 / 3

# ==========================================
# 2. 3D VISUALIZATION SETUP
# ==========================================
fig = plt.figure(figsize=(10, 8))
ax = fig.add_subplot(111, projection='3d')

# Make room at the bottom for the slider UI
plt.subplots_adjust(bottom=0.25) 
initial_n = 5

def draw_curtain(n):
    """
    Clears the 3D axis and draws the Riemann Sum approximation 
    of the line integral (the "curtain") based on n segments.
    """
    ax.clear()
    
    # --- A. Draw the True Mathematical Curves for Reference ---
    # Create 100 points for a smooth continuous curve
    t_smooth = np.linspace(0, np.pi, 100)
    xs_true, ys_true = x_t(t_smooth), y_t(t_smooth)
    zs_true = f(xs_true, ys_true)
    
    # Base curve C on the ground (xy-plane, z=0)
    ax.plot(xs_true, ys_true, 0, color='red', linewidth=2, label='Base Curve C (z=0)')
    # Top edge of the curtain
    ax.plot(xs_true, ys_true, zs_true, color='blue', linewidth=2, label='Top Edge f(x,y)')
    
    # --- B. Calculate and Draw the Riemann Sum Panels ---
    # Divide the interval [0, pi] into n equal sub-intervals
    t_edges = np.linspace(0, np.pi, n + 1)
    approx_area = 0
    panels = []
    
    for i in range(n):
        t_left = t_edges[i]
        t_right = t_edges[i+1]
        
        # Use the midpoint rule to evaluate the height of the function
        t_mid = (t_left + t_right) / 2
        x_mid, y_mid = x_t(t_mid), y_t(t_mid)
        height = f(x_mid, y_mid)
        
        # Calculate Delta s (Arc length of this small segment)
        # Since radius = 1, arc length is simply delta theta (delta t)
        delta_s = t_right - t_left
        
        # Add the area of this rectangular panel to the total approximation
        approx_area += height * delta_s
        
        # Define the 4 corners of the 3D rectangular panel (flat top)
        x0, y0 = x_t(t_left), y_t(t_left)
        x1, y1 = x_t(t_right), y_t(t_right)
        
        verts = [
            (x0, y0, 0),      # Bottom-left
            (x1, y1, 0),      # Bottom-right
            (x1, y1, height), # Top-right
            (x0, y0, height)  # Top-left
        ]
        panels.append(verts)
        
    # Render the flat panels in 3D space
    # alpha=0.6 makes the curtain slightly transparent so we can see through it
    collection = Poly3DCollection(panels, facecolors='cyan', edgecolors='black', linewidths=1, alpha=0.6)
    ax.add_collection3d(collection)
    
    # --- C. Environment Decoration ---
    ax.set_xlim([-1.2, 1.2])
    ax.set_ylim([-0.2, 1.2])
    ax.set_zlim([0, 3.5])
    ax.set_xlabel('X-axis')
    ax.set_ylabel('Y-axis')
    ax.set_zlabel('Z-axis (Height)')
    
    # Dynamically update the title to show the approaching limits
    ax.set_title(f"Line Integral as Area of a Curtain (n={n} segments)\n"
                 f"Riemann Approx: {approx_area:.4f} | Exact Math Area: {EXACT_AREA:.4f}", 
                 fontsize=13, pad=15)
    ax.legend()

# Initial render
draw_curtain(initial_n)

# ==========================================
# 3. INTERACTIVE SLIDER UI
# ==========================================
ax_slider = plt.axes([0.2, 0.1, 0.6, 0.03])
n_slider = Slider(
    ax=ax_slider,
    label='Number of Segments (n)',
    valmin=2,      # Minimum 2 segments
    valmax=50,     # Maximum 50 segments
    valinit=initial_n,
    valstep=1,     # Must be integer steps
    color='cyan'
)

def update(val):
    """Event handler for slider change."""
    n = int(n_slider.val)
    draw_curtain(n)
    fig.canvas.draw_idle()

n_slider.on_changed(update)

# Show the 3D interactive plot
plt.show()