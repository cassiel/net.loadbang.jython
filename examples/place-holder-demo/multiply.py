#	A simple demo of script switching. (There are obviously neater
#	ways to achieve this.)

print("[multiply]")

def _int(inlet, x):
	if inlet == 1:
		_int.tmp = x
	else:
		_int.main = x
		bang(0)

_int.tmp = 0
_int.main = 0

def bang(inlet):
	maxObject.outlet(0, _int.main * _int.tmp)
