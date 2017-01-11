#/usr/bin/python

import sys,re,ctypes

wires = {};
gates = {};

isUnary = re.compile(r'^(NOT)? ?(\d+|[a-z]+)$');
isBinary = re.compile(r'^([a-z]+|\d+) (AND|OR|[LR]SHIFT) ([a-z]+|\d+)$');
isInt = re.compile(r'^\d+$');
isWire = re.compile(r'^[a-z]+$');

def DoNot(x):
    return 65535 - value(x)
def DoAnd(x,y):
    return int(x) & int(y);
def DoOr(x,y):
    return int(x) | int(y);
def DoLeftShift(x,y):
    return int(x) << int(y);
def DoRightShift(x,y):
    return int(x) >> int(y);

Ops = { 'AND' : DoAnd,
        'OR'  : DoOr,
        'LSHIFT' : DoLeftShift,
        'RSHIFT' : DoRightShift,
        }

def value(x):
    return ctypes.c_uint16(int(x)).value

           
def evaluate(signal):

    if (isInt.match(signal)):
        return signal;

    #Known
    if (signal in wires):
        return wires[signal];
    
    #Not Known Yet
    unary = isUnary.match(gates[signal])
    if (unary):
        op = unary.group(1)
        x = evaluate(unary.group( 2 ))
        if (isInt.match(x)):
            if (op): # NOT
                x = DoNot(x);
            wires[signal] = str(x);
            return wires[signal];
    binary = isBinary.match(gates[signal])
    if (binary):
        x = evaluate(binary.group( 1 ))
        op = binary.group( 2 )
        y = evaluate(binary.group( 3 ))
        if (isInt.match(x) and isInt.match(y)):
            wires[signal] = str(Ops[op](x,y) % 65535)
            return wires[signal]
    return None
           

for input in sys.stdin:
    input = input.rstrip();
    input = re.match(r'(.+) -> (\w+)',input)
    gates[input.group(2)] = input.group(1)

a = evaluate('a');
print a
wires = {}
wires['b'] = a

a = evaluate('a');
print a



