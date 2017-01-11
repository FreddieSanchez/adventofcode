#!/usr/bin/python

import sys, itertools;

input = [input.rstrip() for input in sys.stdin]
def squeeze(xs):
    count = 0
    result = []
    prev = None;

    for x in xs:
        if x == prev or prev == None:
            count += 1
        else:
            result.append(str(count));
            result.append(prev)
            count = 1
        prev = x
    if (prev):
        result.append(str(count));
        result.append(prev)

    return ''.join(result)

def look_and_say(s):
    say = [];
    for key, group in itertools.groupby(s):
        say.append(str(len(list(group))))
        say.append(key)
    return ''.join(say)

    
for xi in input:
    i = xi; 
    for x in range(40):
        i = squeeze(i)
    print len(i)

    i = xi; 
    for x in range(40):
        i = look_and_say(i);
    print len(i)

    i = xi; 
    for x in range(50):
        i = look_and_say(i);
    print len(i)
