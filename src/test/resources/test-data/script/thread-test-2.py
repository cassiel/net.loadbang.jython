from java.lang.Thread import currentThread

t2 = currentThread()

if t1 == t2:
    maxObject.error("same thread in two engines")
else:
    maxObject.post("thread test OK")
