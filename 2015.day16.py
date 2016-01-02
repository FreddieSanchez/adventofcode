import itertools, re, sys
                           
def read():
    input = [i.rstrip() for i in sys.stdin]
    regex = re.compile(r'^Sue \d+: (children|cats|samoyeds|pomeranians|akitas|vizslas|goldfish|trees|cars|perfumes): (\d+), (children|cats|samoyeds|pomeranians|akitas|vizslas|goldfish|trees|cars|perfumes): (\d+), (children|cats|samoyeds|pomeranians|akitas|vizslas|goldfish|trees|cars|perfumes): (\d+)$')
    sues = []
    for i in input:
        match = re.match(regex,i)
        contents = {}
        contents[match.group(1)] = int(match.group(2))
        contents[match.group(3)] = int(match.group(4))
        contents[match.group(5)] = int(match.group(6))
        sues.append(contents);
    return sues


sues = read();

analysis = {};
analysis['children'] =  3
analysis['cats'] =  7
analysis['samoyeds'] = 2
analysis['pomeranians'] = 3
analysis['akitas'] = 0
analysis['vizslas'] = 0
analysis['goldfish'] = 5
analysis['trees'] = 3
analysis['cars'] = 2
analysis['perfumes'] = 1

possible = []
for i,sue in enumerate(sues):
    valid = True
    for k,v in sue.iteritems():
        if analysis[k] != v:
          valid = False
    if valid:
        possible.append((i+1,sue))


print possible
