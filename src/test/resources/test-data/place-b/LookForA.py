# Fail if we can (still) see place-a in our path.

import sys, re

print(sys.path)

failed = 0

for x in sys.path:
    if re.search('place-a', x) != None:
        maxObject.outlet(0, 'FAIL')
        failed = 1

if failed == 0:
    maxObject.outlet(0, "OK")
