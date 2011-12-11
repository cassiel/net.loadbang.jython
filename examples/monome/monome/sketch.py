class Sketch:
    def __init__(self, monome):
        "Initialise a sketch object over a Monome."
        self.monome = monome
        self.clear()

    def clear(self):
        "Clear frame buffer: set each column to zero."
        self.columns = [0 for _ in range(0, self.monome.width)]
        return self

    def set(self, x, y, state):
        "Set a single pixel to 0 or 1."
        word = self.columns[x]
        bit = 1 << y
        if state == 0:
            word = word & (0xFFFF ^ bit)
        else:
            word = word & bit
        self.columns[x] = word

    def setCol(self, x, word):
        "Set an entire column to a bit pattern."
        self.columns[x] = word
        return self

    def flip(self, x, y):
        "Flip a single pixel."
        word = self.columns[x]
        bit = 1 << y
        self.columns[x] = word ^ bit

    def flush(self):
        "Flush all changes."
        for x in range(0, len(self.columns)):
            word = self.columns[x]
            if (word != self.monome.lastColumns[x]):
                self.monome.col_bits(x, word & 0xFF, word >> 8)
                self.monome.lastColumns[x] = word
        return self
