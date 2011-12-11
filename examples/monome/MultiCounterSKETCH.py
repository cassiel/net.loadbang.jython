# Individual binary counters on columns: button press flips on/off.

from monome.monomes import SketchMonome

monome = SketchMonome()
sketch = monome.newSketch()

def bang():
    for col in range(0, 8):
        if bang.running[col]:
            sketch.setCol(col, bang.bits[col])
            bang.bits[col] = (bang.bits[col] + 1) % 256
    sketch.flush()

bang.running = [False for _ in range(0, 8)]
bang.bits = [0 for _ in range(0, 8)]

def press(x, y, n):
    if n == 1:
        bang.running[x] = not(bang.running[x])

sketch.clear().flush()
