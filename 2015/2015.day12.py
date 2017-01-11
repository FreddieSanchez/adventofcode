#!/usr/bin/python
import sys, re, json

def countNums(obj,exception):
    total = 0;
    if isinstance(obj, dict):
        if exception in obj.itervalues():
            total = 0;
        else:
            nums = [x for x in obj.itervalues() if isinstance(x,(int, long))]
            total = sum(nums)

            for key, value in obj.iteritems():
                total += countNums(value,exception)

    elif isinstance(obj, list):
        nums = [x for x in obj if isinstance(x,(int, long))]
        total = sum(nums)
        for value in obj:
            total += countNums(value,exception)
    return total



input = [ input.rstrip() for input in sys.stdin ]
for i in input:
    nums = re.findall(r'-?\d+',i)
    nums = [int(x) for x in nums]

    j = json.loads(i)
    print countNums(j,'red')

