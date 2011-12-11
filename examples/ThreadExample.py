#	Simple thread example.

from threading import Thread
import time

running = True

class MyThread(Thread):
    def __init__(self):
        Thread.__init__(self)

    def run(self):
	while running:
            print("Hello World")
            time.sleep(2)

def start():
    MyThread().start()

#	Add a closure which stops the thread. This is triggered
#	when the MXJ instance is deleted, or when the binding
#	environment is cleared.

def _cleanup():
    print("Cleaning up")
    stop()

engine.addCleanup(_cleanup)

def stop():
    global running
    running = False
