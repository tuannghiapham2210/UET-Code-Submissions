import numpy as np
import matplotlib.pyplot as plt

x = np.linspace(-6, 6, 500)
y = np.linspace(-6, 6, 500)
X, Y = np.meshgrid(x, y)
Z = np.sin(X**2 + Y**2) * np.cos(X - Y) * np.exp(-(X**2 + Y**2)/15)

U, S, Vt = np.linalg.svd(Z, full_matrices=False)

k_values = [5, 20, 50]

fig, axs = plt.subplots(2, 2, figsize=(12, 12))
fig.patch.set_facecolor('#2e3440')

axs[0, 0].imshow(Z, cmap='viridis')
axs[0, 0].set_title(f"Original Matrix (Rank = {min(Z.shape)})", color='#eceff4', pad=15, fontsize=14, fontweight='bold')
axs[0, 0].axis('off')

coords = [(0, 1), (1, 0), (1, 1)]

for idx, k in enumerate(k_values):
    Z_approx = U[:, :k] @ np.diag(S[:k]) @ Vt[:k, :]
    row, col = coords[idx]
    axs[row, col].imshow(Z_approx, cmap='viridis')
    axs[row, col].set_title(f"Compressed (k = {k} Singular Values)", color='#eceff4', pad=15, fontsize=14, fontweight='bold')
    axs[row, col].axis('off')

plt.tight_layout(pad=3.0)
plt.show()