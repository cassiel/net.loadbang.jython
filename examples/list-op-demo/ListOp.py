#	A binary list operation object which allows its operation to be
#	dynamically replaced.

from listops.classes import Append, Intersect, Reverse, Register

def append(_):		_list.operation = Append()
def intersect(_):	_list.operation = Intersect()
def reverse(_):		_list.operation = Reverse()
def register(_):	_list.operation = Register()

# Python's varargs are implemented as tuples, not lists.

def _list(inlet, *A):
	if inlet == 1:
		_list.tmp = list(A)
	else:
		_list.main = list(A)
		bang(0)

_list.tmp = []
_list.main = []

def bang(inlet):
	maxObject.outlet(0, _list.operation.op(_list.main, _list.tmp))

#	append by default:
append(None)
