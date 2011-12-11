import re
import base64
import doit

START = re.compile("^-*begin_max5_patcher-*$")
END = re.compile("^-*end_max5_patcher-*$")

running = False
buffer = ""

def text(*A):
    global running, buffer

    for x in A:
        if START.match(x):
            print "START"
            running = True
            buffer = ""
        elif END.match(x):
            print "END"
            data = base64.decodestring(buffer)
            doit.gunzip(data)
##          f=open('xxxxxx', 'wb')
##          f.write(data)
##          f.close()
        else:
            buffer = buffer + x
            print(">>> " + x)
