import Numeric (showHex)

data Command = U | R | D | L deriving(Show)
type Commands = [Command]

type Button = Int

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
move :: Button -> Command -> Button
move i c = 
  case c of 
  U -> if (i `elem` [1,2,3]) then i else i - 3
  R -> if (i `elem` [3,6,9]) then i else i + 1
  D -> if (i `elem` [7,8,9]) then i else i + 3 
  L -> if (i `elem` [1,4,7]) then i else i - 1

--     1
--   2 3 4
-- 5 6 7 8 9
--   A B C
--     D
move2 :: Button -> Command -> Button
move2 i c =
  case c of 
  U -> if (i `elem` [1,2,4,5,9]) then i else if (i `elem` [6,7,8,0xA,0xB,0xC]) then i - 4 else  i - 2
  R -> if (i `elem` [1,4,9,0xC,0xD]) then i else i + 1 
  D -> if (i `elem` [5,0xA, 0xD, 0xC, 0x9]) then i else if (i `elem` [2,3,4,6,7,8]) then i + 4 else  i + 2
  L -> if (i `elem` [1,2,5,0xA,0xD]) then i else i - 1 
 

toHex :: [Button] -> [String]
toHex button = map (\x -> showHex x "") button

solution1 :: [Commands] -> [Button]
solution1 commands = tail $  scanl (foldl move) 5 commands

solution2 :: [Commands] -> [Button]
solution2 commands = tail $ scanl (foldl move2) 5 commands

main = do
  contents <- getContents 
  print $ toHex $ solution1 $ parser contents
  print $ toHex $ solution2 $ parser contents
