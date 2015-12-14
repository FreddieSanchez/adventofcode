import requests, sys

input = raw_input();

list = [item for item in input ]
print list.count('(') - list.count(')');

