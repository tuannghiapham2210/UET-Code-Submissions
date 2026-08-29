import numpy as np
import matplotlib.pyplot as plt
from matplotlib.widgets import Slider

v1 = np.array([3.0, 1.0])
v2 = np.array([1.5, 3.5])

fig, ax = plt.subplots(figsize=(8, 8))
fig.patch.set_facecolor('#2e3440')
ax.set_facecolor('#2e3440')
plt.subplots_adjust(bottom=0.25)

ax.set_xlim(-1, 4)
ax.set_ylim(-1, 4)
ax.set_aspect('equal')
for spine in ax.spines.values():
    spine.set_color('#4c566a')
ax.tick_params(colors='#d8dee9')
ax.grid(True, color='#3b4252', linestyle='--')

ax.quiver(0, 0, v1[0], v1[1], color='#4c566a', angles='xy', scale_units='xy', scale=1, width=0.012)
ax.quiver(0, 0, v2[0], v2[1], color='#4c566a', angles='xy', scale_units='xy', scale=1, width=0.012)

u1 = v1
proj_v2_u1 = (np.dot(v2, u1) / np.dot(u1, u1)) * u1
u2 = v2 - proj_v2_u1

u1_quiver = ax.quiver(0, 0, 0, 0, color='#88c0d0', angles='xy', scale_units='xy', scale=1, width=0.015, zorder=3)
u2_quiver = ax.quiver(0, 0, 0, 0, color='#bf616a', angles='xy', scale_units='xy', scale=1, width=0.015, zorder=3)
proj_quiver = ax.quiver(0, 0, 0, 0, color='#ebcb8b', angles='xy', scale_units='xy', scale=1, width=0.012, zorder=2)
dashed_line, = ax.plot([], [], color='#ebcb8b', linestyle='--', linewidth=1.5)

ax_slider = plt.axes([0.2, 0.1, 0.6, 0.03], facecolor='#3b4252')
step_slider = Slider(ax_slider, 'Step (0 to 2)', 0.0, 2.0, valinit=0.0, color='#a3be8c')
step_slider.label.set_color('#eceff4')
step_slider.valtext.set_color('#eceff4')

def update(val):
    step = step_slider.val
    if step < 1:
        u1_quiver.set_UVC(u1[0] * step, u1[1] * step)
        proj_quiver.set_UVC(0, 0)
        u2_quiver.set_UVC(0, 0)
        dashed_line.set_data([], [])
    elif step <= 2:
        t = step - 1
        u1_quiver.set_UVC(u1[0], u1[1])
        proj_quiver.set_UVC(proj_v2_u1[0] * t, proj_v2_u1[1] * t)
        dashed_line.set_data([v2[0], proj_v2_u1[0]*t + v2[0]*(1-t)], [v2[1], proj_v2_u1[1]*t + v2[1]*(1-t)])
        u2_quiver.set_UVC(u2[0] * t, u2[1] * t)
    fig.canvas.draw_idle()

update(0)
step_slider.on_changed(update)
ax.set_title("Gram-Schmidt Process: Orthogonal Projection", color='#eceff4', pad=20, fontweight='bold', fontsize=14)
plt.show()