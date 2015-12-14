import sys


class Santa:
  def __init__(self):
      self.visited = [(0,0)];
      self.position = (0,0);

  def move(self,c):
      if (c == '^'):
          self.position = (self.position[0], self.position[1]+1);
      elif (c == 'v'):
          self.position = (self.position[0], self.position[1]-1);
      elif (c == '>'):
          self.position = (self.position[0] + 1, self.position[1]);
      elif (c == '<'):
          self.position = (self.position[0] - 1, self.position[1]);

      if self.position not in self.visited:
          self.visited.append(self.position) 


for input in sys.stdin:
    S1 = Santa();
    S2 = Santa();
    santas = (S1,S2);

    for i,c in enumerate(input):
      santas[i%2].move(c); 

s = set(S1.visited + S2.visited)
print len(s);
