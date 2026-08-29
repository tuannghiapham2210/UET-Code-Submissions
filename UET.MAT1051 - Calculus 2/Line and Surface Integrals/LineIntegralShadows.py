import numpy as np
import matplotlib.pyplot as plt
from mpl_toolkits.mplot3d.art3d import Poly3DCollection

# ==========================================
# 1. MATHEMATICAL MODEL SETUP 
# C: Upper half of unit circle, t from 0 to pi
# f(x, y) = 2 + x^2 * y
# ==========================================
def x_t(t): return np.cos(t)
def y_t(t): return np.sin(t)
def f(x, y): return 2 + (x**2) * y

n = 40 # Resolution (number of vertical strips)
t_edges = np.linspace(0, np.pi, n + 1)

# ==========================================
# 2. VISUALIZATION SETUP
# ==========================================
fig = plt.figure(figsize=(12, 10))
ax = fig.add_subplot(111, projection='3d')
ax.set_title("The Ultimate View: The 3D Fence (ds) vs Its Shadows (dx, dy)", fontsize=14, pad=20)

# Lists to hold the 3D polygons and their colors
panels_fence = []
panels_dx = []
colors_dx = []
panels_dy = []
colors_dy = []

# Define the positions of the "walls" where shadows are cast
WALL_Y = 1.5   # XZ wall (for dx shadow)
WALL_X = -1.5  # YZ wall (for dy shadow)

for i in range(n):
    # Calculate midpoints for better height approximation (Riemann Midpoint Rule)
    t_left, t_right = t_edges[i], t_edges[i+1]
    t_mid = (t_left + t_right) / 2
    
    x0, y0 = x_t(t_left), y_t(t_left)
    x1, y1 = x_t(t_right), y_t(t_right)
    h = f(x_t(t_mid), y_t(t_mid))
    
    # ----------------------------------------------------
    # 1. THE ACTUAL FENCE (Integral of f(x,y) ds)
    # ----------------------------------------------------
    # This follows the exact curved path of C
    verts_fence = [(x0, y0, 0), (x1, y1, 0), (x1, y1, h), (x0, y0, h)]
    panels_fence.append(verts_fence)
    
    # ----------------------------------------------------
    # 2. SHADOW ON XZ-PLANE (Integral of f(x,y) dx)
    # ----------------------------------------------------
    verts_dx = [(x0, WALL_Y, 0), (x1, WALL_Y, 0), (x1, WALL_Y, h), (x0, WALL_Y, h)]
    panels_dx.append(verts_dx)
    
    # Color logic: Red if moving backwards (dx < 0)
    delta_x = x1 - x0
    colors_dx.append('cyan' if delta_x > 0 else 'salmon')
    
    # ----------------------------------------------------
    # 3. SHADOW ON YZ-PLANE (Integral of f(x,y) dy)
    # ----------------------------------------------------
    verts_dy = [(WALL_X, y0, 0), (WALL_X, y1, 0), (WALL_X, y1, h), (WALL_X, y0, h)]
    panels_dy.append(verts_dy)
    
    # Color logic: Cyan if forward (dy > 0), Red if backward (dy < 0)
    delta_y = y1 - y0
    colors_dy.append('cyan' if delta_y > 0 else 'salmon')

# --- RENDER THE COLLECTIONS ---
# 1. The Actual Fence (Semi-transparent Greenish-Yellow to stand out)
collection_fence = Poly3DCollection(panels_fence, facecolors='lime', edgecolors='green', linewidths=0.5, alpha=0.5)
ax.add_collection3d(collection_fence)

# 2. The dx Shadow (on the back wall)
collection_dx = Poly3DCollection(panels_dx, facecolors=colors_dx, edgecolors='gray', linewidths=0.2, alpha=0.6)
ax.add_collection3d(collection_dx)

# 3. The dy Shadow (on the left wall)
collection_dy = Poly3DCollection(panels_dy, facecolors=colors_dy, edgecolors='gray', linewidths=0.2, alpha=0.6)
ax.add_collection3d(collection_dy)

# --- DRAW CONNECTING LINES (The "Light Rays") ---
# Draw some dashed lines to show how the fence projects onto the walls
for i in range(0, n, 5):
    x_pt, y_pt = x_t(t_edges[i]), y_t(t_edges[i])
    h_pt = f(x_pt, y_pt)
    # Ray to XZ wall
    ax.plot([x_pt, x_pt], [y_pt, WALL_Y], [h_pt, h_pt], color='gray', linestyle=':', alpha=0.5)
    # Ray to YZ wall
    ax.plot([x_pt, WALL_X], [y_pt, y_pt], [h_pt, h_pt], color='gray', linestyle=':', alpha=0.5)

# --- ENVIRONMENT & DECORATION ---
t_smooth = np.linspace(0, np.pi, 100)
xs, ys, zs = x_t(t_smooth), y_t(t_smooth), f(x_t(t_smooth), y_t(t_smooth))

# Base Curve C and Top Surface Curve
ax.plot(xs, ys, 0, color='black', linewidth=2, label='Base Curve C')
ax.plot(xs, ys, zs, color='blue', linewidth=2, label='Top Edge f(x,y)')

# Adjust axes limits for a perfect cubic view
ax.set_xlim([-1.5, 1.5])
ax.set_ylim([-0.5, 1.5])
ax.set_zlim([0, 3.5])
ax.set_xlabel('X-axis (dx)')
ax.set_ylabel('Y-axis (dy)')
ax.set_zlabel('Z-axis (Height)')

# Add a text box to explain the colors
text_str = ("LEGEND:\n"
            "Green Fence: Real Area (Integral ds)\n"
            "Cyan Shadow: Positive Area (+)\n"
            "Red Shadow: Negative Area (-)")
ax.text2D(0.05, 0.85, text_str, transform=ax.transAxes, fontsize=10, 
          bbox=dict(facecolor='white', alpha=0.8, edgecolor='black'))

plt.legend(loc='upper right')
plt.tight_layout()
plt.show()