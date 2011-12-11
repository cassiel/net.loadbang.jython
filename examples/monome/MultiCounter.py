# Individual binary counters on columns: button press flips on/off.

from monome.monomes import BaseMonome

monome = BaseMonome()

def bang():
    for col in range(0, 8):
        if bang.running[col]:
            monome.col_bits(col, bang.bits[col])
            bang.bits[col] = (bang.bits[col] + 1) % 256

bang.running = [False for _ in range(0, 8)]
bang.bits = [0 for _ in range(0, 8)]

def press(x, y, n):
    if n == 1:
        bang.running[x] = not(bang.running[x])

monome.all(0)
