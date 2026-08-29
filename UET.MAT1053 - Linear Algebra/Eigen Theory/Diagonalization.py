import numpy as np
import matplotlib.pyplot as plt
from matplotlib.widgets import Slider

A = np.array([[2, 1], [1, 2]])
eigenvalues, eigenvectors = np.linalg.eig(A)
P = eigenvectors
D = np.diag(eigenvalues)
P_inv = np.linalg.inv(P)

fig, ax = plt.subplots(figsize=(8, 8))
fig.patch.set_facecolor('#2e3440')
ax.set_facecolor('#2e3440')
plt.subplots_adjust(bottom=0.25)

ax_slider = plt.axes([0.2, 0.1, 0.6, 0.03], facecolor='#3b4252')
t_slider = Slider(ax_slider, 'Step (0 to 3)', 0.0, 3.0, valinit=0.0, color='#b48ead')
t_slider.label.set_color('#eceff4')
t_slider.valtext.set_color('#eceff4')

x = np.linspace(-5, 5, 11)
y = np.linspace(-5, 5, 11)
X, Y = np.meshgrid(x, y)
points = np.vstack([X.flatten(), Y.flatten()])

lines = []
for _ in range(X.shape[0] * 2):
    line, = ax.plot([], [], color='#88c0d0', alpha=0.4, linewidth=1.2)
    lines.append(line)

def get_matrix(t):
    if t < 1:
        return (1 - t) * np.eye(2) + t * P_inv
    elif t < 2:
        return (2 - t) * P_inv + (t - 1) * (D @ P_inv)
    else:
        return (3 - t) * (D @ P_inv) + (t - 2) * A

def update(val):
    t = t_slider.val
    M = get_matrix(t)
    transformed_points = M @ points
    
    X_trans = transformed_points[0, :].reshape(X.shape)
    Y_trans = transformed_points[1, :].reshape(Y.shape)
    
    idx = 0
    for i in range(X.shape[0]):
        lines[idx].set_data(X_trans[i, :], Y_trans[i, :])
        idx += 1
        lines[idx].set_data(X_trans[:, i], Y_trans[:, i])
        idx += 1
        
    fig.canvas.draw_idle()

ax.set_xlim(-8, 8)
ax.set_ylim(-8, 8)
ax.set_aspect('equal')
for spine in ax.spines.values():
    spine.set_color('#4c566a')
ax.tick_params(colors='#d8dee9')
ax.set_title("Diagonalization: I → P⁻¹ → D P⁻¹ → P D P⁻¹", color='#eceff4', pad=20, fontweight='bold', fontsize=14)

update(0)
t_slider.on_changed(update)
plt.show()