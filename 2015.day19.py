import sys,re,itertools


def read():
    input = [i.strip() for i in sys.stdin] 
    regex = re.compile(r'^(\w+) => (\w+)$');

    subs = {}
    molecule =''
    for i in input:
        match = re.match(regex,i)
        if match:
            if match.group(1) in subs:
                subs[match.group(1)].append(match.group(2))
            else:
                subs[match.group(1)] = [match.group(2)]

        if re.match(r'^$',i):
            continue;

        match = re.match(r'^(\w+)$',i)
        if match:
            molecule = match.group(1)

    return (subs,molecule)

subs, molecule = read()

combos = []
for k,val in subs.iteritems(): 
    for v in val:
        i = 0
        while k in molecule[i+len(k):]:
            found = molecule[:i] + re.sub(k,v,molecule[i:],count=1) 
            combos.append(found)
            i = molecule[i:].index(k) + len(k)

print len(set(combos))
