from java.lang.Thread import currentThread

t1 = currentThread()

engine2.setVar("t1", t1)
engine2.runScript("src/test/resources/test-data/script", "thread-test-2.py")
