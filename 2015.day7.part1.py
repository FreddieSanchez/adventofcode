#/usr/bin/python

import sys,re

wires = {};

isSignal = re.compile(r'(NOT)? ?(\d+|[a-z]+) -> ([a-z]+)');
isGate = re.compile(r'([a-z]+|\d+) (AND|OR|[LR]SHIFT) ([a-z]+|\d+) -> ([a-z]+)');
isInt = re.compile(r'^\d+$');
isWire = re.compile(r'^[a-z]+$');

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

def updateWires(z,signal):
    for key in wires.keys():
        updated = re.sub(r'^{0} '.format(z), r'{0} '.format(signal), wires[key])
        if (wires[key] != updated):
          wires[key] = updated; 
          eval(wires[key] + ' -> ' + key);
        
        updated = re.sub(r' {0} '.format(z), r' {0} '.format(signal), wires[key])
        if (wires[key] != updated):
          wires[key] = updated; 
          eval(wires[key] + ' -> ' + key);

def eval(expression):
   
    if (isInt.match(expression)):
        return expression

    if (isWire.match(expression)):
        if (expression in wires and isInt.match(wires[expression])):
            return wires[expression];
        else:
            return expression;
   
    signal = isSignal.match(expression)
    if (signal):
        op = signal.group(1)
        x = eval(signal.group( 2 ))
        z = signal.group( 3 )
        print signal.groups();
        if (isInt.match(x)):
            if (op): # NOT
                x = ~x
            wires[z] = str(x);
            updateWires(z,str(x));
        else:
            if (op):
                wires[z] = op + ' ' + x;
            else:
                wires[z] = x;
        return wires[z];

    gate = isGate.match(expression)
    if (gate):
        x = eval(gate.group( 1 ))
        op = gate.group( 2 )
        y = eval(gate.group( 3 ))
        z = gate.group( 4 )

        if (isInt.match(x) and isInt.match(y)):
            wires[z] = str(Ops[op](x,y))
            updateWires(z,wires[z]);
        else:
            wires[z] = gate.group(1) + ' ' + op + ' ' + gate.group(3)
        return wires[z]
            

           
           

for input in sys.stdin:
    input = input.rstrip();
    eval(input);


print wires;

