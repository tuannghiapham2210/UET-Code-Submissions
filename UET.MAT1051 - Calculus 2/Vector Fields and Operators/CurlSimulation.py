import numpy as np
import matplotlib.pyplot as plt

def simulate_curl():
    # 1. Define the spatial grid
    # Create a 2D grid of x and y coordinates ranging from -5 to 5
    x = np.linspace(-5, 5, 20)
    y = np.linspace(-5, 5, 20)
    X, Y = np.meshgrid(x, y)
    
    # Define grid spacing for numerical differentiation
    dx = x[1] - x[0]
    dy = y[1] - y[0]

    # 2. Define Vector Field 1: Rigid Body Rotation (F = -y*i + x*j)
    # This field represents a perfect circular vortex (Curl != 0)
    P1 = -Y
    Q1 = X
    
    # Calculate numerical partial derivatives using np.gradient
    # np.gradient returns (derivative_along_rows, derivative_along_columns)
    # Rows correspond to the y-axis, columns correspond to the x-axis
    dP1_dy, dP1_dx = np.gradient(P1, dy, dx)
    dQ1_dy, dQ1_dx = np.gradient(Q1, dy, dx)
    
    # Calculate the 2D Curl (z-component): (dQ/dx - dP/dy)
    curl1_z = dQ1_dx - dP1_dy

    # 3. Define Vector Field 2: Radial/Divergent Flow (F = x*i + y*j)
    # This field represents particles moving straight outwards (Curl == 0)
    P2 = X
    Q2 = Y
    
    dP2_dy, dP2_dx = np.gradient(P2, dy, dx)
    dQ2_dy, dQ2_dx = np.gradient(Q2, dy, dx)
    
    curl2_z = dQ2_dx - dP2_dy

    # 4. Visualization using Matplotlib
    fig, (ax1, ax2) = plt.subplots(1, 2, figsize=(14, 6))

    # Plot 1: Vector Field with Curl
    # Use contourf to plot the magnitude of the curl as background color
    contour1 = ax1.contourf(X, Y, curl1_z, cmap='coolwarm', alpha=0.6)
    # Use quiver to plot the vector field arrows
    ax1.quiver(X, Y, P1, Q1, color='black', pivot='mid')
    ax1.set_title("Vortex Field: F = -y i + x j\n(Curl $\\neq$ 0)", fontsize=14)
    ax1.set_xlabel("X-axis")
    ax1.set_ylabel("Y-axis")
    # FIXED: assigned ax=ax1 
    fig.colorbar(contour1, ax=ax1, label="Curl Magnitude (z-component)")

    # Plot 2: Vector Field without Curl
    contour2 = ax2.contourf(X, Y, curl2_z, cmap='coolwarm', alpha=0.6, vmin=-2, vmax=2)
    ax2.quiver(X, Y, P2, Q2, color='black', pivot='mid')
    ax2.set_title("Radial Field: F = x i + y j\n(Curl = 0)", fontsize=14)
    ax2.set_xlabel("X-axis")
    ax2.set_ylabel("Y-axis")
    # FIXED: assigned ax=ax2
    fig.colorbar(contour2, ax=ax2, label="Curl Magnitude (z-component)")

    plt.tight_layout()
    plt.show()

if __name__ == "__main__":
    simulate_curl()