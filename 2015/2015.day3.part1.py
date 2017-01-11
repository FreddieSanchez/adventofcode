import sys


visited = [(0,0)];
position = (0,0);

for input in sys.stdin:
    for c in input:
        if (c == '^'):
            position = (position[0], position[1]+1);
        elif (c == 'v'):
            position = (position[0], position[1]-1);
        elif (c == '>'):
            position = (position[0] + 1, position[1]);
        elif (c == '<'):
            position = (position[0] - 1, position[1]);
        if position not in visited:
            visited.append(position) 
    
print len(visited)
