import numpy as np
import matplotlib.pyplot as plt

def visualize_triple_integral():
    # ---------------------------------------------------------
    # 1. DEFINE THE DISCRETE SPACE (The dx, dy, dz elements)
    # ---------------------------------------------------------
    # We create a bounding box from -2 to 2 in all directions.
    # 'resolution' defines how many cubes we slice the box into.
    resolution = 30
    x_range = np.linspace(-2, 2, resolution)
    y_range = np.linspace(-2, 2, resolution)
    z_range = np.linspace(-2, 2, resolution)
    
    # Calculate the volume of our discrete 'dV' cube
    dx = x_range[1] - x_range[0]
    dy = y_range[1] - y_range[0]
    dz = z_range[1] - z_range[0]
    dV = dx * dy * dz

    # Create the 3D grid
    X, Y, Z = np.meshgrid(x_range, y_range, z_range, indexing='ij')

    # ---------------------------------------------------------
    # 2. DEFINE THE DOMAIN V (Limits of Integration)
    # ---------------------------------------------------------
    # Our object is a sphere of radius R = 2.
    # We create a boolean mask to only keep points inside the sphere.
    R = 2.0
    inside_sphere = (X**2 + Y**2 + Z**2) <= R**2
    
    # Filter the coordinates to only include those inside the sphere
    X_vol = X[inside_sphere]
    Y_vol = Y[inside_sphere]
    Z_vol = Z[inside_sphere]

    # ---------------------------------------------------------
    # 3. DEFINE THE DENSITY FUNCTION p(x,y,z)
    # ---------------------------------------------------------
    # Density is highest at the origin and decays outwards: e^(-(x^2+y^2+z^2))
    density = np.exp(-(X_vol**2 + Y_vol**2 + Z_vol**2))

    # ---------------------------------------------------------
    # 4. CALCULATE TOTAL MASS (The Riemann Sum)
    # ---------------------------------------------------------
    # Mass = Sum of (density * dV) for all points inside the volume
    total_mass = np.sum(density * dV)
    print(f"Calculated Mass of the object: {total_mass:.4f} units")

    # ---------------------------------------------------------
    # 5. VISUALIZATION
    # ---------------------------------------------------------
    fig = plt.figure(figsize=(10, 8))
    ax = fig.add_subplot(111, projection='3d')

    # To make the visual clear, we use the density values to dictate 
    # the color (cmap) and we make less dense points more transparent (alpha).
    # We sort the points by density so the densest points are drawn last (on top)
    sort_indices = np.argsort(density)
    X_plot = X_vol[sort_indices]
    Y_plot = Y_vol[sort_indices]
    Z_plot = Z_vol[sort_indices]
    density_plot = density[sort_indices]

    # Plot using scatter
    sc = ax.scatter(
        X_plot, Y_plot, Z_plot, 
        c=density_plot,        # Color mapped to density
        cmap='magma',          # 'magma' goes from black (low) to bright white/yellow (high)
        s=20,                  # Size of the scatter points
        alpha=0.3,             # Transparency allows us to see "inside" the volume
        edgecolors='none'
    )

    # Add a color bar to explain the density mapping
    cbar = plt.colorbar(sc, ax=ax, shrink=0.6, pad=0.1)
    cbar.set_label('Density $\\rho(x,y,z)$', fontsize=12)

    # Formatting the plot
    ax.set_title("Triple Integral Visualization: Mass Density of a Sphere", fontsize=14)
    ax.set_xlabel('X axis')
    ax.set_ylabel('Y axis')
    ax.set_zlabel('Z axis')
    
    # Ensure axes have equal aspect ratio so the sphere doesn't look stretched
    ax.set_box_aspect((1, 1, 1))

    plt.tight_layout()
    plt.show()

if __name__ == "__main__":
    visualize_triple_integral()