# Simple script which flips the state of all the LEDs on a button press.

from monome.monomes import BaseMonome

monome = BaseMonome()

def press(x, y, state):
    "flip all the lights if the button press is down"
    if state == 1:
        monome.all(press.newState)
        press.newState = not(press.newState)

press.newState = 1

def bang():
    "ignore the metro"
    pass

monome.all(0)
