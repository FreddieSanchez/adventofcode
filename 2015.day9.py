#!/usr/bin/python

import sys, re;

# City -> [(City,distance)]
distances = {}

def path(starting,comp):

    previous = None;
    current = starting;
    currentDistance = 0;

    unvisited = [city for city in distances ]

    cur_path = [];
    
    # while 
    while len(unvisited):

        unvisited.remove(current);

        cur_path.append(current);
                
        if (previous):
            currentDistance += distances[previous][current]
        
        # get all neighbors
        neighbors = distances[current];

        # filter out all neighbors that we have already visited
        neighbors = [neighbor for neighbor in neighbors if neighbor in unvisited]
        print [(x,distances[current][x]) for x in neighbors]

        previous = current;
        if (len(neighbors)):
            current = comp(neighbors,key=lambda x: distances[previous][x])


    return {'path': cur_path,
            'distance':currentDistance};

            


input = re.compile(r'(\w+) to (\w+) = (\d+)');
for x in sys.stdin:
    match = input.match(x.rstrip())
    fro = match.group(1)
    to   = match.group(2)
    distance = int(match.group(3))
    if fro not in distances:
        distances[fro] = {} 
    if to not in distances:
        distances[to] = {}
    distances[fro][to] = distance
    distances[to][fro] = distance

paths = [path(city,min) for city in distances]
print min(paths, key=lambda x: x['distance'])

paths = [path(city,max) for city in distances]
print max(paths, key=lambda x: x['distance'])
