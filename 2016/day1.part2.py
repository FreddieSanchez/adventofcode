# --- Day 1: No Time for a Taxicab ---
# 
# --- Part Two ---
# 
# Then, you notice the instructions continue on the back of the Recruiting Document. Easter Bunny HQ is actually at the first location you visit twice.
# 
# For example, if your instructions are R8, R4, R4, R8, the first location you visit twice is 4 blocks away, due East.
# 
# How many blocks away is the first location you visit twice?

import sys


def changeDirection(direction, adjustment):
    direction+= adjustment;
    if direction < 0:
        direction = 270;
    else:
        direction %= 360

    return direction;

def degreesToCompass(direction):
    if direction == 0: # North
        return "North"
    elif direction == 90: # East
        return "East"
    elif direction == 180: # South
        return "South"
    elif direction == 270: # West
        return "West"

def advance(location, direction, distance):
    if direction == 0: # North
        location['y'] += distance;
    elif direction == 90: # East
        location['x'] += distance;
    elif direction == 180: # South
        location['y'] -= distance;
    elif direction == 270: # West
        location['x'] -= distance;

    print 'advance', distance, direction, degreesToCompass(direction), location;
    return location;

def calculateDiffDistance(oldLocation, newLocation):
    return abs(oldLocation['x'] - newLocation['x']) + abs(oldLocation['y'] - newLocation['y'])
 
# Read Input
input = "".join(sys.stdin)
input = input.rstrip();
input = input.split(", ")
print len(input);

originalLocation = {'x':0, 'y':0};
currentLocation = {'x':0, 'y':0};
currentDirection = 0 # 0 -> North, 90 -> East, 180 -> South, 270 -> West

visitedLocation = [];
visitedLocation.append(currentLocation.copy());
for x in input:
   degrees =  90 if x[0] == 'R' else -90;
   print x, degrees
   currentDirection = changeDirection(currentDirection, degrees);
   currentLocation = advance(currentLocation, currentDirection, int(x[1:]));
   visitedLocation.append(currentLocation.copy());

print originalLocation;
print currentLocation;
print calculateDiffDistance(originalLocation, currentLocation);

