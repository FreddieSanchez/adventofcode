data Turn = R | L deriving(Show)
data CardinalDirection = N | E | S | W deriving(Show, Enum)

turn compass direction = mod ( (case direction of R -> succ ; L -> pred ) .  compass ) 4

move positions (direction, distance) =
 | 

solution = do
  contents <- getContents 
  let commands = 
    [case direction of
      'R':distance -> (R, read distance :: Int)
      'L':distance -> (L, read distance :: Int)
     | direction <- (words (filter (/=',') contents)]
  in
  scanl move [(0,0,N)] commands 

main = do
  print solution
