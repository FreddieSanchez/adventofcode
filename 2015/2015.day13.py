
import sys,re,itertools


def solve(peeps):
    all = itertools.permutations(list(peeps.iterkeys()))

    totals = []
    for combo in all:
        prev = combo[-1]
        total = []
        for person in combo:
            total.append(people[prev][person])
            total.append(people[person][prev])
            prev = person
        totals.append(sum(total))
    return max(totals)
        



people = {}
input = [i.rstrip() for i in sys.stdin]
regex = re.compile(r'^(\w+) would (gain|lose) (\d+) happiness units by sitting next to (\w+).$')
for i in input:
    match = regex.match(i)
    name = match.group(1)
    gain = match.group(2)
    points = int(match.group(3))
    nextTo = match.group(4)

    if name not in people:
        people[name] = {} 
    if  nextTo not in people:
        people[nextTo] = {}
    people[name][nextTo] = points if (gain == 'gain') else points * -1


print solve(people)
me = 'Freddie'
people[me] = {}
for name in people.iterkeys():
    people[name][me] = 0
    people[me][name] = 0
print solve(people)

