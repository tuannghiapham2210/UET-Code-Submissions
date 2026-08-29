import numpy as np
import matplotlib.pyplot as plt
from matplotlib.patches import Polygon

def plot_det_scaling(ax, matrix, title):
    x = np.linspace(-5, 5, 11)
    y = np.linspace(-5, 5, 11)
    X, Y = np.meshgrid(x, y)
    
    points = np.vstack([X.flatten(), Y.flatten()])
    transformed_points = matrix @ points
    
    X_trans = transformed_points[0, :].reshape(X.shape)
    Y_trans = transformed_points[1, :].reshape(Y.shape)
    
    for i in range(X.shape[0]):
        ax.plot(X_trans[i, :], Y_trans[i, :], color='#88c0d0', alpha=0.15, linewidth=1)
        ax.plot(X_trans[:, i], Y_trans[:, i], color='#88c0d0', alpha=0.15, linewidth=1)

    i_hat = np.array([1, 0])
    j_hat = np.array([0, 1])
    new_i = matrix @ i_hat
    new_j = matrix @ j_hat
    
    orig_poly = Polygon(np.array([[0,0], i_hat, i_hat+j_hat, j_hat]), closed=True, 
                        facecolor='#4c566a', edgecolor='#4c566a', alpha=0.4, zorder=1)
    ax.add_patch(orig_poly)
    
    trans_poly = Polygon(np.array([[0,0], new_i, new_i+new_j, new_j]), closed=True, 
                         facecolor='#b48ead', edgecolor='#b48ead', alpha=0.7, zorder=2)
    ax.add_patch(trans_poly)
    
    ax.quiver(0, 0, new_i[0], new_i[1], color='#bf616a', angles='xy', scale_units='xy', scale=1, width=0.012, zorder=3)
    ax.quiver(0, 0, new_j[0], new_j[1], color='#a3be8c', angles='xy', scale_units='xy', scale=1, width=0.012, zorder=3)
    
    det_val = np.linalg.det(matrix)
    ax.text(0.04, 0.96, f'det = {det_val:.1f}', transform=ax.transAxes, 
            fontsize=13, color='#ebcb8b', fontweight='bold', va='top')
    
    ax.set_xlim(-4, 4)
    ax.set_ylim(-4, 4)
    ax.set_aspect('equal')
    ax.set_title(title, fontsize=14, pad=10, color='#eceff4', fontweight='bold')
    
    ax.set_facecolor('#2e3440')
    for spine in ax.spines.values():
        spine.set_color('#4c566a')
    ax.tick_params(colors='#d8dee9')

fig, axs = plt.subplots(2, 2, figsize=(12, 12))
fig.patch.set_facecolor('#2e3440')

A = np.array([[2, 0], [0, 1.5]])
plot_det_scaling(axs[0, 0], A, "Scaling (det = 3)")

B = np.array([[1, 1.5], [0, 1]])
plot_det_scaling(axs[0, 1], B, "Shear (Area Preserved)")

C = np.array([[-1, 1], [1, 1]])
plot_det_scaling(axs[1, 0], C, "Reflection & Scale (det < 0)")

D = np.array([[1, 1], [1, 1]])
plot_det_scaling(axs[1, 1], D, "Dimensional Collapse (det = 0)")

plt.tight_layout(pad=4.0)
plt.show()