import numpy as np
import matplotlib.pyplot as plt
from matplotlib.widgets import Slider

# Function to clear and redraw the 3D solid sphere based on active slider values
def draw_sphere(ax, radius, max_theta, max_phi):
    # Clear the previous frames to avoid overlay artifacts
    ax.clear()
    
    # Convert degree parameters to radians for mathematical evaluations
    theta_max_rad = np.radians(max_theta)
    phi_max_rad = np.radians(max_phi)
    
    # 1. Generate and render the outer spherical boundary (Shell)
    if theta_max_rad > 0 and phi_max_rad > 0:
        theta_crust = np.linspace(0, theta_max_rad, 60)
        phi_crust = np.linspace(0, phi_max_rad, 60)
        T_crust, P_crust = np.meshgrid(theta_crust, phi_crust)
        
        x_crust = radius * np.sin(P_crust) * np.cos(T_crust)
        y_crust = radius * np.sin(P_crust) * np.sin(T_crust)
        z_crust = radius * np.cos(P_crust)
        
        ax.plot_surface(x_crust, y_crust, z_crust, color='royalblue', alpha=0.5, edgecolor='none')
    
    # 2. Generate and render internal longitudinal cutting planes
    rho_vals = np.linspace(0, radius, 30)
    phi_vals = np.linspace(0, phi_max_rad, 30)
    R_int, P_int = np.meshgrid(rho_vals, phi_vals)
    
    if max_theta < 360.0 and phi_max_rad > 0:
        # Internal flat face at theta = 0
        x_cut1 = R_int * np.sin(P_int) * np.cos(0)
        y_cut1 = R_int * np.sin(P_int) * np.sin(0)
        z_cut1 = R_int * np.cos(P_int)
        ax.plot_surface(x_cut1, y_cut1, z_cut1, color='darkgray', alpha=0.8, edgecolor='none')

        # Internal flat face at theta = max_theta
        x_cut2 = R_int * np.sin(P_int) * np.cos(theta_max_rad)
        y_cut2 = R_int * np.sin(P_int) * np.sin(theta_max_rad)
        z_cut2 = R_int * np.cos(P_int)
        ax.plot_surface(x_cut2, y_cut2, z_cut2, color='darkgray', alpha=0.8, edgecolor='none')
        
    # 3. Generate and render latitudinal capping surface
    if max_phi < 180.0 and theta_max_rad > 0:
        theta_vals = np.linspace(0, theta_max_rad, 30)
        R_cap, T_cap = np.meshgrid(rho_vals, theta_vals)
        
        x_cap = R_cap * np.sin(phi_max_rad) * np.cos(T_cap)
        y_cap = R_cap * np.sin(phi_max_rad) * np.sin(T_cap)
        z_cap = R_cap * np.cos(phi_max_rad)
        ax.plot_surface(x_cap, y_cap, z_cap, color='lightgray', alpha=0.8, edgecolor='none')
        
    # Standardize labels and fix limits to prevent axis jumping during updates
    ax.set_xlabel('X Axis')
    ax.set_ylabel('Y Axis')
    ax.set_zlabel('Z Axis')
    
    # Keep the bounding box constant (maximum potential radius is 5.0)
    fixed_limit = 5.5
    ax.set_xlim(-fixed_limit, fixed_limit)
    ax.set_ylim(-fixed_limit, fixed_limit)
    ax.set_zlim(-fixed_limit, fixed_limit)

# Initialize the main figure window
fig = plt.figure(figsize=(11, 9))

# Allocate specific viewport space for the 3D plot to make room for sliders below
ax = fig.add_axes([0.1, 0.25, 0.8, 0.7], projection='3d')

# Execute the initial draw with default values
draw_sphere(ax, radius=3.0, max_theta=270.0, max_phi=135.0)

# Define separate axes regions to house each individual slider component
ax_radius = fig.add_axes([0.25, 0.15, 0.55, 0.025])
ax_theta  = fig.add_axes([0.25, 0.10, 0.55, 0.025])
ax_phi    = fig.add_axes([0.25, 0.05, 0.55, 0.025])

# Instanciate the Slider widgets with boundaries and starting allocations
s_radius = Slider(ax_radius, 'Radius', 1.0, 5.0, valinit=3.0, valfmt='%1.1f')
s_theta  = Slider(ax_theta, 'Max Theta (°)', 0.0, 360.0, valinit=270.0, valfmt='%1.0f°')
s_phi    = Slider(ax_phi, 'Max Phi (°)', 0.0, 180.0, valinit=135.0, valfmt='%1.0f°')

# Callback trigger linked to the event system to initiate updates upon sliding
def update_plot(val):
    draw_sphere(ax, s_radius.val, s_theta.val, s_phi.val)
    fig.canvas.draw_idle()  # Redraw the rendering canvas efficiently

# Bind the update trigger directly to each slider's value-change event
s_radius.on_changed(update_plot)
s_theta.on_changed(update_plot)
s_phi.on_changed(update_plot)

# Display the interactive environment window
plt.show()