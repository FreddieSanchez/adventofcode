#/usr/bin/python

import sys, hashlib, itertools, re

for input in sys.stdin:
    hash = '';
    match = '';
    input = input.rstrip('\n');
    for x in itertools.count():
        m = hashlib.md5();
        m.update(input + str(x));
        hash = m.hexdigest();
        match = re.search(r"^0{5}",hash)
        if (match):
            break;
    print x,hash

