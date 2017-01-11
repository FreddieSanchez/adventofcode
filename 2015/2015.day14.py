import sys, re, math


class Reindeer:
    def __init__(self, name, speed, duration,rest):
        self.name = name;
        self.speed = speed;
        self.duration = duration;
        self.rest = rest;
        self.points = 0
    

    def fly(self,seconds):
        
        return self.speed * self.duration * (seconds / (self.duration + self.rest)) +\
               self.speed * min([self.duration,(seconds % (self.duration + self.rest))])

    def point(self,p):
        self.points += p



regex=re.compile(r'^(\w+) can fly (\d+) km\/s for (\d+) seconds, but then must rest for (\d+) seconds\.$')

input = [i.rstrip() for i in sys.stdin]
time = 2503

reindeers = []
for r in input: 
    match = re.match(regex, r)
    reindeers.append(Reindeer(match.group(1),\
                              int(match.group(2)),\
                              int(match.group(3)),\
                              int(match.group(4))))
distance = [ r.fly(time) for r in reindeers ]
print max(distance)

for t in range(1,time + 1):
    distance = [ r.fly(t) for r in reindeers ]
    maximum = max(distance)
    for x,r in zip(distance,reindeers):
        if (x == maximum):
            r.point(1)
print max([r.points for r in reindeers])

