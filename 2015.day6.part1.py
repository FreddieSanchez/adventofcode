#/usr/bin/python

import sys,re

col_count = 1000;
row_count = 1000;
board = [[ False for x in xrange(col_count) ] for x in xrange(row_count)]

for input in sys.stdin:
    input = input.rstrip();
    r = re.compile(r'(turn on|turn off|toggle) (\d+),(\d+) through (\d+),(\d+)')
    m = r.match(input)
    cmd = m.group(1)
    bottom = (int(m.group(2)),int(m.group(3)))
    top = (int(m.group(4)),int(m.group(5)))
    
    for x in xrange(bottom[0],top[0]+1):
        for y in xrange(bottom[1],top[1]+1):
            if (cmd == 'turn on'):
                board[x][y] = True;
            elif (cmd == 'turn off'):
                board[x][y] = False;
            elif (cmd == 'toggle'):
                board[x][y] = not board[x][y];

print [ board[col][row] for col in xrange(col_count) for row in xrange(row_count)].count(True);
