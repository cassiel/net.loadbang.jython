# Pop3 email reader, built as proof of Zawinski's Law. It shows From and Subject
# lines in a cellblock.

import poplib
import re

def maxObject_outlet(col, row, str):
#    print('c=%d r=%d: %s' % (col, row, str))
    maxObject.outlet(0, ['set', col, row, str])

def go():
    print(go.pophost)
    print(go.username)
    print(go.password)

    con = poplib.POP3(go.pophost)
    con.user(go.username)
    con.pass_(go.password)

    numMessages = len(con.list()[1])
    print(numMessages)

    for i in range(numMessages):
        for j in con.top(i+1,0)[1]:
            fr = re.findall(r'^From: .*', j)
            su = re.findall(r'^Subject: .*', j)

            if len(fr) != 0:
                maxObject_outlet(0, i, fr[0])

            if len(su) != 0:
                maxObject_outlet(1, i, su[0])

    con.quit()

go.fromIdx = 0
go.subjIdx = 0

def pophost(h):
    go.pophost = h

def username(u):
    go.username = u

def password(p):
    go.password = p
