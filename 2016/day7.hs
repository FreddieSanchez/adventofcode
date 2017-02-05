import Data.List.Split (splitWhen, divvy)
import Data.Maybe (mapMaybe)

data IP7 = IP7 { left :: String,
                 middle :: String,
                 right :: String
               }  deriving (Show)


isABBA [] = False
isABBA str
  | length str < 4 = False
  | otherwise = case str of 
      a:b:c:d:_ -> (a == d) && (b == c) && (a /= b)
      _ -> False

containsABBA str = 
  any isABBA (divvy 4 1 str)

ipContainsABBA ip = (not ( containsABBA (middle ip) )) && (containsABBA (left ip) || containsABBA (right ip))

makeIP ip = 
  case splitWhen (\s -> s == '[' || 
                       s == ']') ip of 
    left:middle:right:_ -> 
        Just IP7 { left = left, middle = middle, right  = right};
     _ -> Nothing;

solution1 ips =  filter ipContainsABBA $ mapMaybe makeIP ips

main = do
  contents <- getContents 
  let parsed = lines contents;
  print $ solution1 parsed 
