# Void script which clears the LEDs and does nothing else.

import config
from monome.monomes import BaseMonome

monome = BaseMonome()

def bang():
    "ignore the metro"
    pass

def press(x, y, n):
    pass

monome.all(0)
