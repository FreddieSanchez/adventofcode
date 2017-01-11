#/usr/bin/python

import sys,re

count = 0;
def vowels(input):
    return None != re.search(r'(\w*[aeiou]\w*)(\w*[aeiou]\w*)(\w*[aeiou]\w*)',input);

def repeats(input):
    return None != re.search(r'^.*(\w)\1.*$',input)

def excludes(input):
    return None == re.search(r'(ab|cd|pq|xy)',input)

for input in sys.stdin:
    input = input.rstrip();
    if (vowels(input) and repeats(input) and excludes(input)):
        count += 1
print count
