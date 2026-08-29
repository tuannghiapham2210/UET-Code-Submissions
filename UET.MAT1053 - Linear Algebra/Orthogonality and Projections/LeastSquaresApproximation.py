import numpy as np
import matplotlib.pyplot as plt

np.random.seed(42)
X = np.linspace(1, 9, 15)
true_slope = 1.8
true_intercept = 2.5
Y = true_slope * X + true_intercept + np.random.normal(0, 2.5, 15)

A = np.vstack([X, np.ones(len(X))]).T
theta = np.linalg.inv(A.T @ A) @ A.T @ Y
Y_pred = A @ theta

fig, ax = plt.subplots(figsize=(10, 6))
fig.patch.set_facecolor('#2e3440')
ax.set_facecolor('#2e3440')

ax.scatter(X, Y, color='#88c0d0', s=60, zorder=3, label='Data (b vector)')
ax.plot(X, Y_pred, color='#bf616a', linewidth=2.5, zorder=2, label='Projection (Ax = b̂)')

for i in range(len(X)):
    ax.plot([X[i], X[i]], [Y[i], Y_pred[i]], color='#ebcb8b', linestyle='--', alpha=0.8, zorder=1)

ax.set_xlim(0, 10)
ax.set_ylim(0, 25)
for spine in ax.spines.values():
    spine.set_color('#4c566a')
ax.tick_params(colors='#d8dee9')
ax.grid(True, color='#3b4252', linestyle='--', alpha=0.6)

legend = ax.legend(facecolor='#3b4252', edgecolor='#4c566a', fontsize=11)
for text in legend.get_texts():
    text.set_color('#eceff4')

ax.set_title("Least Squares Approximation", color='#eceff4', pad=20, fontweight='bold', fontsize=14)
plt.show()