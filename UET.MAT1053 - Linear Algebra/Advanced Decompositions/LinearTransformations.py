import numpy as np
import matplotlib.pyplot as plt

def plot_transformation(ax, matrix, title):
    x = np.linspace(-5, 5, 11)
    y = np.linspace(-5, 5, 11)
    X, Y = np.meshgrid(x, y)
    
    points = np.vstack([X.flatten(), Y.flatten()])
    transformed_points = matrix @ points
    
    X_trans = transformed_points[0, :].reshape(X.shape)
    Y_trans = transformed_points[1, :].reshape(Y.shape)
    
    for i in range(X.shape[0]):
        ax.plot(X_trans[i, :], Y_trans[i, :], color='#88c0d0', alpha=0.3, linewidth=1.5)
        ax.plot(X_trans[:, i], Y_trans[:, i], color='#88c0d0', alpha=0.3, linewidth=1.5)
        
    i_hat = matrix @ np.array([1, 0])
    j_hat = matrix @ np.array([0, 1])
    
    ax.quiver(0, 0, i_hat[0], i_hat[1], color='#bf616a', angles='xy', scale_units='xy', scale=1, width=0.015, zorder=3)
    ax.quiver(0, 0, j_hat[0], j_hat[1], color='#a3be8c', angles='xy', scale_units='xy', scale=1, width=0.015, zorder=3)
    
    ax.set_xlim(-6, 6)
    ax.set_ylim(-6, 6)
    ax.set_aspect('equal')
    ax.set_title(title, fontsize=14, pad=10, color='#eceff4', fontweight='bold')
    
    ax.set_facecolor('#2e3440')
    ax.spines['bottom'].set_color('#4c566a')
    ax.spines['top'].set_color('#4c566a') 
    ax.spines['right'].set_color('#4c566a')
    ax.spines['left'].set_color('#4c566a')
    ax.tick_params(colors='#d8dee9')

fig, axs = plt.subplots(2, 2, figsize=(12, 12))
fig.patch.set_facecolor('#2e3440')

I = np.array([[1, 0], [0, 1]])
plot_transformation(axs[0, 0], I, "Original Space (Identity Matrix)")

theta = np.pi / 3
R = np.array([[np.cos(theta), -np.sin(theta)], [np.sin(theta), np.cos(theta)]])
plot_transformation(axs[0, 1], R, "Rotation (60 degrees)")

S = np.array([[1, 1.5], [0, 1]])
plot_transformation(axs[1, 0], S, "Shear (x-axis)")

Sc = np.array([[2, 0], [0, -1.5]])
plot_transformation(axs[1, 1], Sc, "Scaling & Reflection")

plt.tight_layout(pad=4.0)
plt.show()