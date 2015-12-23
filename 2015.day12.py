#!/usr/bin/python
import sys, re


input = [ input.rstrip() for input in sys.stdin ]
for i in input:
    nums = re.findall(r'-?\d+',i)
    nums = [int(x) for x in nums]
    print sum(nums)
