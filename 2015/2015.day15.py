import itertools, re, sys
from functools import reduce

class Ingredient:
    def __init__(self,name,capacity,durability,flavor,texture,calories):
        self.name = name
        self.capacity = capacity
        self.durability = durability
        self.flavor = flavor
        self.texture = texture
        self.calories = calories
    def stats(self):
        return [self.capacity,self.durability,self.flavor,self.texture,self.calories]


                           
def read():
    input = [i.rstrip() for i in sys.stdin]
    regex = re.compile(r'^(\w+): capacity (-?\d+), durability (-?\d+), flavor (-?\d+), texture (-?\d+), calories (-?\d+)$')

    ingredients = []
    for i in input:
        match = re.match(regex,i)
        ingredient = Ingredient(match.group(1), int(match.group(2)), int(match.group(3)), int(match.group(4)), int(match.group(5)), int(match.group(6)))
        ingredients.append(ingredient)
    return ingredients


def partition(value):
    to = value + 1
    for i in xrange(0,to):
        for j in xrange(0,to-i):
            for k in xrange(0,to-i-j):
                yield (i,j,k,value-i-j-k) 

ingredients = read();
answer = []
for p in partition(100):
    calc = map(lambda (quantity, ingredient): \
               map(lambda x:    \
                   quantity * x,\
                   ingredient.stats()),\
             zip(p,ingredients))

    cap = sum([x[0] for x in calc]) 
    dur = sum([x[1] for x in calc])
    flav = sum([x[2] for x in calc]) 
    text = sum([x[3] for x in calc]) 
    calorie = sum([x[4] for x in calc])
    
    score = map(lambda v: max([0,v]),\
               [cap,dur,flav,text,calorie])
    answer.append((reduce(lambda a,b: a * b,score[:-1]),score[-1]))
    
print max(x[0] for x in answer)
print max(x[0] for x in answer if x[1] == 500)

      

    



