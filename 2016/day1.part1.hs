data Command = R Int | L Int deriving(Show)
data CardinalDirection = N | E | S | W deriving(Show, Enum, Eq, Ord)
data Position = Position { x:: Int, y::Int, d::CardinalDirection } deriving(Show)

instance Eq Position where
  (==) p1 p2 = (x p1) == (x p2) && (y p1) == (y p2) 

type Commands = [Command]
type Positions = [Position]

commandDistance :: Command -> Int
commandDistance (R d) = d
commandDistance (L d) = d

face :: CardinalDirection -> Command -> CardinalDirection
face compass direction = toEnum ( mod ( ( fromEnum compass) + (case direction of R _ -> 1; L _ -> ( -1 ) ) ) 4 )

move :: Positions -> Command -> Positions
move positions command  = 
  let currentPosition = last positions
  in 
    case face (d currentPosition) command  of
       N -> [currentPosition {y=(y currentPosition) + i,d= N} | i <- [1..distance]]
       E -> [currentPosition {x=(x currentPosition) + i,d= E} | i <- [1..distance]]
       S -> [currentPosition {y=(y currentPosition) - i,d= S} | i <- [1..distance]]
       W -> [currentPosition {x=(x currentPosition) - i,d= W} | i <- [1..distance]]
       where 
       distance = commandDistance command
        
navigate :: Position -> Commands -> Positions
navigate position commands = concat $ scanl move [position] commands

positionDifference :: Position -> Position -> Int
positionDifference p1 p2 =  (absDiff (x p1) (x p2)) + (absDiff (y p1) (y p2)) where absDiff a b = abs (a - b)

solution1 :: Commands -> Int
solution1 commands =  do
  let initialPosition =(Position {x=0,y=0,d=N}) 
  let path = navigate initialPosition commands
  positionDifference (last path) initialPosition


repeats :: Positions -> Positions
repeats [] = []
repeats (p:ps) = if p `elem` ps then p:(repeats ps) else (repeats ps)

solution2 :: Commands -> Int
solution2 commands = 
  let initialPosition =(Position {x=0,y=0,d=N}) 
  in 
     let path = navigate initialPosition commands
     in 
     positionDifference (head (repeats path)) initialPosition
      

parser :: String -> Commands
parser contents = do
    [case direction of
      'R':distance -> R (parse distance)
      'L':distance -> L (parse distance)
     | direction <- words (filter (/=',') contents)]
     where 
     parse x = read x :: Int

main = do
  contents <- getContents 
  print $ solution1 $ parser contents
  print $ solution2 $ parser contents


