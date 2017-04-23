import Data.List.Split (split, startsWithOneOf, divvy)
import Data.List (isInfixOf)
import Data.Maybe (mapMaybe)

data IP7 = IP7 { inside:: [String],
                 outside:: [String]
               }  deriving (Show)


isABBA [] = False
isABBA str
  | length str < 4 = False
  | otherwise = case str of 
      a:b:c:d:_ -> (a == d) && (b == c) && (a /= b)
      _ -> False

containsABBA str = 
  any isABBA (divvy 4 1 str)

ipContainsABBA ip = not ( any containsABBA (inside ip) ) && any containsABBA (outside ip)

makeIP ip = 
  foldl buildUp IP7 { inside = [], outside = [] } $ split (startsWithOneOf "[]") ip
  where 
    buildUp ip chunk = 
     case head chunk of
       '[' -> ip {inside = inside ip ++ [tail chunk]} 
       ']' -> ip {outside = outside ip ++ [tail chunk] }
       _   -> ip {outside = outside ip ++ [chunk] } 
      

-- part 2
isABA:: String -> Bool
isABA str
  | length str < 3 = False
  | otherwise = case str of 
      a:b:c:_ -> (a == c) && (a /= b)
      _ -> False

makeBABofABA :: String -> String
makeBABofABA  str
  | otherwise = case str of 
      a:b:c:_ -> b:a:b:[]
      _ -> str


containsABAandBAB :: IP7 -> String -> Bool 
--String -> String -> [String]
containsABAandBAB ip outside =
  foldl contains False (divvy 3 1 outside)
  where
  contains :: Bool -> String -> Bool
  contains currentReturn aba = 
   case currentReturn of 
     True -> currentReturn
     False ->  case (isABA aba) of 
                 True -> any (isInfixOf (makeBABofABA aba)) (inside ip) 
                 False -> currentReturn
      

ipContainsABAandBAB :: IP7 -> Bool 
ipContainsABAandBAB ip =
  any ( containsABAandBAB ip ) (outside ip)

solution1 ips = length $ filter ipContainsABBA $ map makeIP ips

solution2 ips = length $ filter ipContainsABAandBAB $ map makeIP ips

main = do
  contents <- getContents 
  let parsed = lines contents;
  --print $ map makeIP parsed
  print $ solution1 parsed 
  print $ solution2 parsed

