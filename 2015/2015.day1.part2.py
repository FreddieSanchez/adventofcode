import requests, sys

def pos(list, val):
    position = 0; 
    for i, x in enumerate(list):
        if x == '(':
            position += 1
        if x == ')':
            position -= 1
        if position == val:
            return i + 1;
    return -1

input = raw_input();

list = [item for item in input ]
print pos(list,-1)


 
