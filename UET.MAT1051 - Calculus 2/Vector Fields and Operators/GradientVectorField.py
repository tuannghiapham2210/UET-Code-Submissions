import numpy as np
import matplotlib.pyplot as plt

# 1. Define the spatial domain based on the slide (from -4 to 4)
X_MIN, X_MAX = -4, 4
Y_MIN, Y_MAX = -4, 4

# --- SET UP FOR LEVEL CURVES (Đường mức) ---
# We use a very dense grid (100x100) to ensure the contour lines are drawn smoothly
x_contour = np.linspace(X_MIN, X_MAX, 100)
y_contour = np.linspace(Y_MIN, Y_MAX, 100)
X_c, Y_c = np.meshgrid(x_contour, y_contour)

# Define the scalar function f(x, y) = x^2*y - y^3
Z = (X_c**2) * Y_c - (Y_c**3)

# --- SET UP FOR GRADIENT VECTOR FIELD (Trường vector gradient) ---
# We use a sparser grid (20x20) for arrows so they don't overlap and clutter the plot
x_vec = np.linspace(X_MIN, X_MAX, 20)
y_vec = np.linspace(Y_MIN, Y_MAX, 20)
X_v, Y_v = np.meshgrid(x_vec, y_vec)

# Define the Gradient Vector Field: nabla f = (2xy)i + (x^2 - 3y^2)j
# U is the partial derivative with respect to x (fx)
# V is the partial derivative with respect to y (fy)
U = 2 * X_v * Y_v
V = (X_v**2) - 3 * (Y_v**2)

# --- VISUALIZATION ---
fig, ax = plt.subplots(figsize=(9, 9))

# Plot 1: Draw the level curves using plt.contour
# 'levels' defines the number of lines, 'cmap' gives a nice color gradient (plasma)
contour_plot = ax.contour(X_c, Y_c, Z, levels=20, cmap='plasma', linewidths=1.5)
# Optional: Add numerical values to the contour lines for clarity
ax.clabel(contour_plot, inline=True, fontsize=8, fmt='%1.0f')

# Plot 2: Draw the gradient vector field using plt.quiver
# color='teal' provides a strong contrast against the plasma contour lines
ax.quiver(X_v, Y_v, U, V, color='teal', angles='xy', scale_units='xy', alpha=0.8)

# Decorate the plot to look mathematical and clean
ax.axhline(0, color='black', linewidth=1.2) # X-axis
ax.axvline(0, color='black', linewidth=1.2) # Y-axis
ax.set_xlim(X_MIN, X_MAX)
ax.set_ylim(Y_MIN, Y_MAX)
ax.grid(color='gray', linestyle='--', linewidth=0.5, alpha=0.5)

# Add descriptive titles and labels
ax.set_title("Orthogonality of Gradient Vector Field and Level Curves\n$f(x,y) = x^2y - y^3$", fontsize=14, pad=15)
ax.set_xlabel("X-axis")
ax.set_ylabel("Y-axis")

# Render the final plot
plt.show()