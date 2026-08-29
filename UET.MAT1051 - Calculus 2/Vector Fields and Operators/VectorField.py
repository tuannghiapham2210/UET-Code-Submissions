import numpy as np
import matplotlib.pyplot as plt
from matplotlib.widgets import Slider

# Define the boundaries of the 2D domain (Tập xác định D)
X_MIN, X_MAX = -5, 5
Y_MIN, Y_MAX = -5, 5

# Create the main figure and axis
# plt.subplots_adjust makes room at the bottom for our UI slider
fig, ax = plt.subplots(figsize=(8, 8))
plt.subplots_adjust(bottom=0.2)

# Set the initial number of vectors along each axis
initial_resolution = 10

def draw_vector_field(resolution):
    """
    Clears the current axis and redraws the vector field based on the given resolution.
    """
    ax.clear() # Clear the previous plot to avoid overlapping
    
    # 1. Create the coordinate grid
    # np.linspace generates exactly 'resolution' points evenly spaced between MIN and MAX
    x = np.linspace(X_MIN, X_MAX, resolution)
    y = np.linspace(Y_MIN, Y_MAX, resolution)
    X, Y = np.meshgrid(x, y)
    
    # 2. Define the Vector Field F(x,y) = -y*i + x*j
    # U represents the horizontal component, V represents the vertical component
    U = -Y
    V = X
    
    # 3. Plot the vector field using quiver
    # We let matplotlib auto-scale the arrow lengths for better visibility at different densities
    ax.quiver(X, Y, U, V, color='teal')
    
    # 4. Decorate the plot to maintain a clean, mathematical look
    ax.axhline(0, color='black', linewidth=1)
    ax.axvline(0, color='black', linewidth=1)
    ax.grid(color='gray', linestyle='--', linewidth=0.5, alpha=0.5)
    ax.set_xlim(X_MIN - 1, X_MAX + 1)
    ax.set_ylim(Y_MIN - 1, Y_MAX + 1)
    
    # Add titles and labels
    ax.set_title("Vector Field F(x,y) = -y*i + x*j", fontsize=14, pad=15)
    ax.set_xlabel("X-axis")
    ax.set_ylabel("Y-axis")

# Render the initial state of the vector field
draw_vector_field(initial_resolution)

# 5. Build the Interactive Slider UI
# Define the position and size of the slider [left, bottom, width, height]
ax_slider = plt.axes([0.2, 0.05, 0.6, 0.03])

# Create the Slider object
# valmin and valmax define the allowed density range. valstep=1 ensures whole numbers.
density_slider = Slider(
    ax=ax_slider,
    label='Grid Density',
    valmin=4,
    valmax=25,
    valinit=initial_resolution,
    valstep=1,
    color='teal'
)

# 6. Define the event handler for slider changes
def update(val):
    """
    Triggered whenever the slider value changes.
    Fetches the new value and updates the plot.
    """
    current_resolution = int(density_slider.val)
    draw_vector_field(current_resolution)
    fig.canvas.draw_idle() # Request a UI redraw

# Attach the update function to the slider's on_changed event
density_slider.on_changed(update)

# Display the application
plt.show()