import numpy as np
import matplotlib.pyplot as plt

# ==========================================
# CLOSED SURFACE ORIENTATION (SPHERE)
# ==========================================

def plot_closed_surface():
    # 1. Generate spherical coordinates
    # theta: azimuthal angle [0, 2pi], phi: polar angle [0, pi]
    theta = np.linspace(0, 2 * np.pi, 20)
    phi = np.linspace(0, np.pi, 10)
    Theta, Phi = np.meshgrid(theta, phi)

    # 2. Convert spherical to Cartesian coordinates (Radius R = 1)
    X = np.sin(Phi) * np.cos(Theta)
    Y = np.sin(Phi) * np.sin(Theta)
    Z = np.cos(Phi)

    # 3. Calculate normal vectors
    # For a sphere centered at the origin (0,0,0), the outward normal vector 
    # at any point (x, y, z) points in the exact same direction as the position vector itself.
    U_out = X
    V_out = Y
    W_out = Z

    # Inward normal points directly towards the origin
    U_in = -X
    V_in = -Y
    W_in = -Z

    # 4. Initialize the figure with 2 subplots
    fig = plt.figure(figsize=(14, 6))

    # --- Subplot 1: Positive Orientation (Outward) ---
    ax1 = fig.add_subplot(121, projection='3d')
    ax1.plot_surface(X, Y, Z, color='cyan', alpha=0.3, edgecolor='gray', linewidth=0.5)
    ax1.quiver(X, Y, Z, U_out, V_out, W_out, length=0.3, color='red', normalize=True)
    ax1.set_title("Positive Orientation (Outward Normal)", pad=20)
    ax1.set_xlim([-1.5, 1.5])
    ax1.set_ylim([-1.5, 1.5])
    ax1.set_zlim([-1.5, 1.5])
    ax1.set_xlabel('X')
    ax1.set_ylabel('Y')
    ax1.set_zlabel('Z')

    # --- Subplot 2: Negative Orientation (Inward) ---
    ax2 = fig.add_subplot(122, projection='3d')
    ax2.plot_surface(X, Y, Z, color='cyan', alpha=0.3, edgecolor='gray', linewidth=0.5)
    ax2.quiver(X, Y, Z, U_in, V_in, W_in, length=0.3, color='purple', normalize=True)
    ax2.set_title("Negative Orientation (Inward Normal)", pad=20)
    ax2.set_xlim([-1.5, 1.5])
    ax2.set_ylim([-1.5, 1.5])
    ax2.set_zlim([-1.5, 1.5])
    ax2.set_xlabel('X')
    ax2.set_ylabel('Y')
    ax2.set_zlabel('Z')

    plt.suptitle("Closed Surface Orientation: Unit Sphere", fontsize=16)
    plt.tight_layout()
    plt.show()

if __name__ == '__main__':
    plot_closed_surface()