data Command = U | R | D | L deriving(Show)
type Commands = [Command]



parser :: String -> [Commands]
parser contents = map (\xs -> 
  (map (\x -> if x == 'U' then U else
              if x == 'R' then R else
              if x == 'D' then D else
              L
         )) xs ) (lines contents)
    
-- 1 2 3
-- 4 5 6
-- 7 8 9
move :: Int -> Command -> Int
move i c = 
  case c of 
  U -> if (i `elem` [1,2,3]) then i else i - 3
  R -> if (i `elem` [3,6,9]) then i else i + 1
  D -> if (i `elem` [7,8,9]) then i else i + 3 
  L -> if (i `elem` [1,4,7]) then i else i - 1

findNumber:: Int -> Commands -> Int
findNumber i c = foldl move i c

solution1 :: [Commands] -> [Int]
solution1 commands = scanl findNumber 5 commands

main = do
  contents <- getContents 
  print $ solution1 $ parser contents
