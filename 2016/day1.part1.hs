data Command = R Int | L Int deriving(Show)
data CardinalDirection = N | E | S | W deriving(Show, Enum, Bounded)
data Position = Position { x:: Int, y::Int, d::CardinalDirection } deriving(Show)

type Commands = [Command]
type Positions = [Position]

commandDistance :: Command => Int
commandDistance (R d) = d
commandDistance (L d) = d

face :: CardinalDirection -> Command => CardinalDirection
face compass direction = toEnum ( mod ( ( fromEnum compass) + (case direction of R _ -> 1; L _ -> ( -1 ) ) ) 4 )

move :: Positions-> Command => Positions
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
        


solution :: Position -> Commands  => Positions
solution position commands = scanl move [(position:[])] commands

parser :: String => Commands
parser contents = do
    [case direction of
      'R':distance -> R (read distance :: Int)
      'L':distance -> L (read distance :: Int)
     | direction <- words (filter (/=',') contents)]

main = do
  contents <- getContents 
  print (solution (Position {x=0,y=0,d=N}) ( parser contents))

