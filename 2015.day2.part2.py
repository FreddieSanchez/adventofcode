import sys

def vol(box):
  return reduce(lambda x,y: x * y, box,1);

def perim(box):
  box1 = box;
  box1.sort();
  return box1[0] * 2 + box1[1] * 2;

sqs = [];

for input in sys.stdin:
    box = [int(x) for x in input.split('x')];
    sq = vol(box) + perim(box);
    sqs.append(sq);

print sum(sqs);
