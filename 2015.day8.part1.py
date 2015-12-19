#!/usr/bin/python
import sys, re;

input = [ line.rstrip() for line in sys.stdin]

sumInMemory = 0;
sumLiteral = 0;
for x in input:
    sumLiteral+= len(x);
for x in input:
    r = re.compile(r'^(".+")$')
    sumInMemory += len(eval(r.match(x).group(1)))

print sumLiteral - sumInMemory

sumEscaped = 0
for x in input:
    sumEscaped += len(re.escape(x)) + 2


print sumEscaped - sumLiteral
