import numpy as np
import matplotlib.pyplot as plt

# ==========================================
# OPEN SURFACE ORIENTATION (PARABOLOID)
# ==========================================

def plot_open_surface():
    # 1. Generate grid data for x and y
    # Using a 15x15 grid prevents the quiver arrows from being too dense and messy
    x = np.linspace(-1.5, 1.5, 15)
    y = np.linspace(-1.5, 1.5, 15)
    X, Y = np.meshgrid(x, y)

    # 2. Define the surface equation: z = f(x, y) = x^2 + y^2
    Z = X**2 + Y**2

    # 3. Calculate normal vectors
    # For z = f(x, y), the upward normal vector n is proportional to (-df/dx, -df/dy, 1)
    # df/dx = 2x, df/dy = 2y
    U_up = -2 * X
    V_up = -2 * Y
    W_up = np.ones_like(Z)

    # Downward normal is simply the exact opposite of the upward normal
    U_down = -U_up
    V_down = -V_up
    W_down = -W_up

    # 4. Initialize the figure with 2 subplots (1 row, 2 columns)
    fig = plt.figure(figsize=(14, 6))

    # --- Subplot 1: Positive Orientation (Upward) ---
    ax1 = fig.add_subplot(121, projection='3d')
    # Plot the surface with a colormap, alpha makes it semi-transparent
    ax1.plot_surface(X, Y, Z, cmap='Blues', alpha=0.6, edgecolor='none')
    # Plot the normal vectors
    # normalize=True ensures all arrows have the same length regardless of the vector magnitude
    ax1.quiver(X, Y, Z, U_up, V_up, W_up, length=0.4, color='red', normalize=True)
    ax1.set_title("Positive Orientation (Upward Normal)", pad=20)
    ax1.set_xlabel('X')
    ax1.set_ylabel('Y')
    ax1.set_zlabel('Z')

    # --- Subplot 2: Negative Orientation (Downward) ---
    ax2 = fig.add_subplot(122, projection='3d')
    ax2.plot_surface(X, Y, Z, cmap='Blues', alpha=0.6, edgecolor='none')
    ax2.quiver(X, Y, Z, U_down, V_down, W_down, length=0.4, color='purple', normalize=True)
    ax2.set_title("Negative Orientation (Downward Normal)", pad=20)
    ax2.set_xlabel('X')
    ax2.set_ylabel('Y')
    ax2.set_zlabel('Z')

    plt.suptitle("Open Surface Orientation: Paraboloid $z = x^2 + y^2$", fontsize=16)
    plt.tight_layout()
    plt.show()

if __name__ == '__main__':
    plot_open_surface()