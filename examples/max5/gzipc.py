# File: GzipConsumer.py
# Copyright (c) 2003 by Fredrik Lundh.

class GzipConsumer:

    def __init__(self, consumer):
        self.__consumer = consumer
        self.__decoder = None
        self.__data = ""

    def __getattr__(self, key):
        # delegate unknown methods/attributes
        return getattr(self.__consumer, key)

    def feed(self, data):
        if self.__decoder is None:
            # check if we have a full gzip header
            data = self.__data + data
            try:
                i = 10
                flag = ord(data[3])
                if flag & 4: # extra
                    x = ord(data[i]) + 256*ord(data[i+1])
                    i = i + 2 + x
                if flag & 8: # filename
                    while ord(data[i]):
                        i = i + 1
                    i = i + 1
                if flag & 16: # comment
                    while ord(data[i]):
                        i = i + 1
                    i = i + 1
                if flag & 2: # crc
                    i = i + 2
                if len(data) < i:
                    raise IndexError("not enough data")
                if data[:3] != "\x1f\x8b\x08":
                    raise IOError("invalid gzip data")
                data = data[i:]
            except IndexError:
                self.__data = data
                return # need more data
            import zlib
            self.__data = ""
            self.__decoder = zlib.decompressobj(-zlib.MAX_WBITS)
        data = self.__decoder.decompress(data)
        if data:
            self.__consumer.feed(data)

    def close(self):
        if self.__decoder:
            data = self.__decoder.flush()
            if data:
                self.__consumer.feed(data)
        self.__consumer.close()
