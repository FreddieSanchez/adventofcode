#/usr/bin/python

import sys,pprint,copy

on = '#'
off = '.'

def neighbors(board,point):
    x,y = point
    return [(x1,y1)  for y1 in [y-1,y,y+1] for x1 in [x-1,x,x+1] if x1 >= 0 and x1 < len(board) and y1 >=0 and y1 < len(board[0]) and (x1 == x and y1 != y or x1 !=x and y1 == y or x1 != x and y1 != y)]

def toggle_light(ref_board, board,point):
    x,y = point
    lights = [ref_board[x1][y1] for x1,y1 in neighbors(ref_board,point)]

    if ref_board[x][y] == on:
      board[x][y] = on if lights.count(on) in [2,3] else off
    
    if ref_board[x][y] == off:
        board[x][y] = on if lights.count(on) in [3] else off

def step(b):
    reference_board = copy.deepcopy(b) 
    for x,x1 in enumerate(b):
        for y,y1 in enumerate(x1):
            toggle_light(reference_board,b,(x,y))
    return b

board = [list(input.rstrip()) for input in sys.stdin ]
original = copy.deepcopy(board);
for x in xrange(100):
    board = step(board)

print sum([x.count(on) for x in board])

board = original
board[0][0] = on
board[0][len(board[0])-1] = on
board[len(board)-1][0] = on
board[len(board)-1][len(board[0])-1] = on

for x in xrange(100):
    board = step(board)
    board[0][0] = on
    board[0][len(board[0])-1] = on
    board[len(board)-1][0] = on
    board[len(board)-1][len(board[0])-1] = on

print sum([x.count(on) for x in board])

