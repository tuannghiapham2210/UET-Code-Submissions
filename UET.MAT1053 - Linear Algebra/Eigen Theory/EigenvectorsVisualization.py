import numpy as np
import matplotlib.pyplot as plt
from matplotlib.widgets import Slider

A = np.array([[2, 1], [0.5, 2]])

fig, ax = plt.subplots(figsize=(8, 8))
fig.patch.set_facecolor('#2e3440')
ax.set_facecolor('#2e3440')
plt.subplots_adjust(bottom=0.25)

ax.set_xlim(-4, 4)
ax.set_ylim(-4, 4)
ax.set_aspect('equal')

for spine in ax.spines.values():
    spine.set_color('#4c566a')
ax.tick_params(colors='#d8dee9')
ax.grid(True, color='#3b4252', linestyle='--')

v_quiver = ax.quiver(0, 0, 1, 0, color='#88c0d0', angles='xy', scale_units='xy', scale=1, width=0.015, zorder=3)
Av_quiver = ax.quiver(0, 0, 2, 0.5, color='#bf616a', angles='xy', scale_units='xy', scale=1, width=0.012, zorder=2)

ax_slider = plt.axes([0.2, 0.1, 0.6, 0.03], facecolor='#3b4252')
theta_slider = Slider(ax_slider, 'Angle', 0.0, 2 * np.pi, valinit=0.0, color='#5e81ac')
theta_slider.label.set_color('#eceff4')
theta_slider.valtext.set_color('#eceff4')

def update(val):
    theta = theta_slider.val
    v = np.array([np.cos(theta), np.sin(theta)])
    Av = A @ v
    
    v_quiver.set_UVC(v[0], v[1])
    Av_quiver.set_UVC(Av[0], Av[1])
    fig.canvas.draw_idle()

theta_slider.on_changed(update)
ax.set_title("Interactive Eigenvectors (v: Blue, Av: Red)", color='#eceff4', pad=20, fontweight='bold', fontsize=14)
plt.show()