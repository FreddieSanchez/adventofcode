#/usr/bin/python

import sys,re

repeats = re.compile(r'.*(..*.).*\1.*')

oneletter = re.compile(r'.*(.).\1.*')


l = []
for input in sys.stdin:
  input = input.rstrip();
  if repeats.match(input) and oneletter.match(input):
    l.append( input );

print len(l);
