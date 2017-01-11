import sys,re,itertools,pprint


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
        for m in re.finditer(k,molecule):
           combos.append(molecule[:m.start()]+v+molecule[m.end():])
        

print len(set(combos))

def replace(current,subs,steps,times):
    if (molecule == current):
        print steps
        times.append(steps)
        return

    for k,val in subs.iteritems(): 
            for v in val:
                for m in re.finditer(k,current):
                   new = dict(subs)
                   new.pop(k)
                   pprint.pprint(current)
                   pprint.pprint(new)
                   replace(current[:m.start()]+v+current[m.end():],\
                           new,steps+1,times)
                


times = []
replace('e',subs,0,times)
print(min(times))

