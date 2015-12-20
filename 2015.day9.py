#!/usr/bin/python

import sys, re, itertools;

# City -> [(City,distance)]
distances = {}

def path_distance(path_list):

    previous = None
    current = None
    currentDistance = 0

    for city in path_list:
        current = city
        if (previous):
            currentDistance += distances[previous][current]
        previous = current
    return currentDistance
        

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

paths = [path for path in itertools.permutations([city for city in distances])]
print min([path_distance(x) for x in paths])
print max([path_distance(x) for x in paths])
