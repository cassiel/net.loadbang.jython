#	Very simple lap timer

from java.lang.System import currentTimeMillis

def lap():
    now = currentTimeMillis()
	
    maxObject.outlet(0, now - lap.startTime)
    lap.startTime = now

lap.startTime = currentTimeMillis()
