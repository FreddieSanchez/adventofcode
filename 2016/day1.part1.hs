data Direction = R | L deriving(Show)

main = do
  contents <- getContents 
  let commands = 
    [case direction of
      'R':distance -> (R, read distance :: Int)
      'L':distance -> (L, read distance :: Int)
     | direction <- (words (filter (/=',') contents)]


