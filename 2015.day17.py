
import sys,itertools as it

containers = [int(i.rstrip()) for i in sys.stdin]
powerset = list(it.chain.from_iterable(it.combinations(containers, r) for r in range(len(containers)+1)))
filtered = filter(lambda s: sum(s) == 150, powerset)
print len(filtered)


