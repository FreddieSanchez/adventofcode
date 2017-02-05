import Data.List.Split (split, startsWithOneOf, divvy)
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
      



solution1 ips = length $ filter ipContainsABBA $ map makeIP ips

main = do
  contents <- getContents 
  let parsed = lines contents;
  --print $ map makeIP parsed
  print $ solution1 parsed 
