import itertools, re, sys

class Ingredient:
    def __init__(self,name,capacity,durability,flavor,texture,calories):
        self.name = name
        self.capacity = capacity
        self.durability = durability
        self.flavor = flavor
        self.texture = texture
        self.calories = calories

    def property(self,quantity,property):
        return quantity * property
                           


input = [i.rstrip() for i in sys.stdin]
regex = re.compile(r'^(\w+): capacity (-?\d+), durability (-?\d+), flavor (-?\d+), texture (-?\d+), calories (-?\d+)$')

ingredients = []
for i in input:
    match = re.match(regex,i)
    ingredient = Ingredient(match.group(1), int(match.group(2)), int(match.group(3)), int(match.group(4)), int(match.group(5)), int(match.group(6)))
    ingredients.append(ingredient)


def partition(value):
    to = value + 1
    for i in range(to):
        for j in range(to-i):
            for k in range(to-i-j):
                for l in range(to-i-j-k):
                    yield (i,j,k,l) 

answer = []
for p in partition(100):
    calc = map(lambda (quantity, ingredient): \
               map(lambda x:    \
                   quantity * x, \
                   [ingredient.capacity,\
                    ingredient.durability,\
                    ingredient.flavor, \
                    ingredient.texture,
                    ingredient.calories]),\
             zip(p,ingredients))

    cap = sum([x[0] for x in calc]) 
    dur = sum([x[1] for x in calc])
    flav = sum([x[2] for x in calc]) 
    text = sum([x[3] for x in calc]) 
    cal = sum([x[4] for x in calc])


    calc = map(lambda v: v if v > 0 else 0,\
               [cap,dur,flav,text])
    answer.append((reduce(lambda a,b: a * b,calc,1),cal))
    
print max([x[0] for x in answer])
print max([y[0] for y in filter(lambda x: x[1] == 500,answer)])

      

    



