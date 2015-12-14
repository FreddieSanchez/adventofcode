import sys

def side(s1, s2):
    return 2 * s1 * s2;

sqs = [];

for input in sys.stdin:
    box = [int(x) for x in input.split('x')];
    sq = [side(box[0],box[1]) , side(box[1],box[2]) , side(box[2],box[0])];

    sqs.append(sum(sq) + min(sq)/2);

print sum(sqs);
