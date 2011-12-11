from gzipc import GzipConsumer

class stupid_gzip_consumer:
    def __init__(self): self.data = []
    def feed(self, data): self.data.append(data)

def gunzip(data):
    c = stupid_gzip_consumer()
    gzc = GzipConsumer(c)
    gzc.feed(data)
    gzc.close()
    return "".join(c.data)
