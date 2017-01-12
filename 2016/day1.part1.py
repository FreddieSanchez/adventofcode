# --- Day 1: No Time for a Taxicab ---
# 
# Santa's sleigh uses a very high-precision clock to guide its movements, and 
# the clock's oscillator is regulated by stars. Unfortunately, the stars have been stolen... by the Easter Bunny. To save Christmas, Santa needs you to retrieve all fifty stars by December 25th.
# 
# Collect stars by solving puzzles. Two puzzles will be made available on each day in the advent calendar; the second puzzle is unlocked when you complete the first. Each puzzle grants one star. Good luck!
# 
# You're airdropped near Easter Bunny Headquarters in a city somewhere. "Near", unfortunately, is as close as you can get - the instructions on the Easter Bunny Recruiting Document the Elves intercepted start here, and nobody had time to work them out further.
# 
# The Document indicates that you should start at the given coordinates (where you just landed) and face North. Then, follow the provided sequence: either turn left (L) or right (R) 90 degrees, then walk forward the given number of blocks, ending at a new intersection.
# 
# There's no time to follow such ridiculous instructions on foot, though, so you take a moment and work out the destination. Given that you can only walk on the street grid of the city, how far is the shortest path to the destination?
# 
# For example:
# 
#     Following R2, L3 leaves you 2 blocks East and 3 blocks North, or 5 blocks away.
#     R2, R2, R2 leaves you 2 blocks due South of your starting position, which is 2 blocks away.
#     R5, L5, R5, R3 leaves you 12 blocks away.
#     How many blocks away is Easter Bunny HQ?


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

for x in input:
   degrees =  90 if x[0] == 'R' else -90;
   print x, degrees
   currentDirection = changeDirection(currentDirection, degrees);
   currentLocation = advance(currentLocation, currentDirection, int(x[1:]));

print originalLocation;
print currentLocation;
print calculateDiffDistance(originalLocation, currentLocation);

