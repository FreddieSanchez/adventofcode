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
     
    x,y = location
    if direction == 0: # North
        newLocation = (x, y + distance);
    elif direction == 90: # East
        newLocation = (x + distance, y);
    elif direction == 180: # South
        newLocation = (x, y - distance);
    elif direction == 270: # West
        newLocation = (x - distance, y);

    #print 'advance', distance, direction, degreesToCompass(direction), newLocation;
    return newLocation;


# returns all points between start and end;
def points(start, end):
  x1, y1 = start;
  x2, y2 = end;
  if (x1 == x2):
    return [(x1,y) for y in xrange(min(y1,y2),max(y1,y2) + (-1 if (max(y1,y2) < 0) else 1))];
  else:
    return [(x,y1) for x in xrange(min(x1,x2),max(x1,x2) + (-1 if (max(x1,x2) < 0) else 1))]

  
  

def calculateDiffDistance(oldLocation, newLocation):
    return abs(oldLocation[0] - newLocation[0]) + abs(oldLocation[1] - newLocation[1])
 
# Read Input
input = "".join(sys.stdin)
input = input.rstrip();
input = input.split(", ")
#print len(input);

originalLocation = (0,0)
currentLocation = (0,0);
currentDirection = 0 # 0 -> North, 90 -> East, 180 -> South, 270 -> West

visitedLocations= set();
solutionFound = False;
for x in input:
   degrees =  90 if x[0] == 'R' else -90;
#   print x, degrees
   currentDirection = changeDirection(currentDirection, degrees);
   previousLocation = currentLocation;
   currentLocation = advance(currentLocation, currentDirection, int(x[1:]));
   visitedPoints = points(previousLocation, currentLocation);

   for point in visitedPoints:
     if point in visitedLocations and point != previousLocation and not solutionFound:
      solution = point;
      solutionFound = True;
       
     visitedLocations.add(point);
  
if solutionFound:

  print solution,calculateDiffDistance(originalLocation,solution);


