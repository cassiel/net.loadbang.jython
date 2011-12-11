# Binary counter on a column: button press changes column.

from monome.monomes import BaseMonome

monome = BaseMonome()

def bang():
    monome.col_bits(bang.col, bang.bits)
    bang.bits = (bang.bits + 1) % 256

bang.bits = 0
bang.col = 0

def press(x, y, n):
    if n == 1:
        bang.col = x

monome.all(0)
