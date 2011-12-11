# We've bundled all the classes into one file, for reasons of laziness.

class Append:
	def op(self, L1, L2):
		L = L1[:]		# Let's not side-effect!
		L.extend(L2)
		return L

class Intersect:
	def op(self, L1, L2):
		L = []
		for x in L1:
			if x in L2:
				L.append(x)
		return L

class Register:
	def op(self, _, L):
		return L

class Reverse:
	def op(self, L1, _):
		L = L1[:]
		L.reverse()
		return L
