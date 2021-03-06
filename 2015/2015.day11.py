#!/usr/bin/python

import sys, re, time

a = ord('a')
z = ord('z')
def increment(n):
    if (n + 1) > z:
        return a
    else: 
        return n + 1

def increment_string(s):

    num = [ ord(c) for c in s];
    
    num.reverse();

    #deal with any carries
    result = []
    result.insert(0,increment(num[0]))
    carry = (num[0] / z == 1)
    
    for x in num[1:]:
        val = x;
        if (carry):
            val = increment(x)
            carry = (x / z == 1)
        else:
            carry = 0
        result.insert(0,val)
        

    return ''.join([chr(x) for x in result])


def meets_requirements(s):
    two_pairs = re.compile(r'.*([a-z])\1.*([^\1])\2.*');
    iol = re.compile(r'.*([iol]).*');

    def incrementing(s):
        prev = s[0]
        incr = False;
        count = 0
        
        for c in s[1:]:
            if (ord(c) == ord(prev) + 1):
                count += 1
                if (not incr):
                    incr= True;
            elif (count < 3):
                count = 1;
                incr= False
            prev = c
        return incr and count >=3
    return two_pairs.match(s) and \
           not iol.match(s) and \
           incrementing(s)

def solve(s):
    init = s;
    s = increment_string(s)
    while not meets_requirements(s) and s != init:
     s = increment_string(s)
    return s


part1 = [solve(input.rstrip()) for input in sys.stdin ]
print part1
part2 = [solve(x) for x in part1];
print part2
