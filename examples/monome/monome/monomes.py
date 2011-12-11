# Some simple utility classes for communicating with a Monome.

import config
from net.loadbang.osc.comms import UDPTransmitter
from net.loadbang.osc.data import Message
from java.net import InetAddress
from monome.sketch import Sketch

class BaseMonome:
    "Encapsulate a connection to a particular Monome, and provide base functions."

    def __init__(self, host=config.monomeHost, port=config.monomePort, prefix=config.monomePrefix, width=config.monomeWidth, height=config.monomeHeight):
        self.transmitter = UDPTransmitter(InetAddress.getByName(host), port)
        self.prefix = prefix
        self.width = width
        self.height = height

    def setupPrefix(self):
        self.transmitter.transmit(Message('/sys/prefix').addString(self.prefix))

    def set(self, x, y, state):
        self.transmitter.transmit(
            Message(self.prefix + "/led")
            .addInteger(x).addInteger(y).addInteger(state)
        )

    def col_bits(self, x, bits1, bits2=0):
        "Set a column by bitmap - optional second bit pattern for 16."
        self.transmitter.transmit(
            Message(self.prefix + "/led_col")
            .addInteger(x).addInteger(bits1).addInteger(bits2)
        )

    def row_bits(self, x, bits1, bits2=0):
        "Set a row by bitmap - optional second bit pattern for 16."
        self.transmitter.transmit(
            Message(self.prefix + "/led_row")
            .addInteger(x).addInteger(bits1).addInteger(bits2)
        )

    def all(self, state):
        self.transmitter.transmit(Message(self.prefix + "/clear").addInteger(state))

    def close(self):
        self.transmitter.close()

class SketchMonome(BaseMonome):
    "A Monome object which can generate Sketch instances."

    def __init__(self, host=config.monomeHost, port=config.monomePort, prefix=config.monomePrefix, width=config.monomeWidth, height=config.monomeHeight):
        BaseMonome.__init__(self, host, port, prefix, width, height)
        self.lastColumns =  [0 for _ in range(0, width)]

    def newSketch(self):
        "Generate and return a new, empty, sketch on this Monome."
        return Sketch(self)
