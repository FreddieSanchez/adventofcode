import Data.List (group, sort, sortBy)

parser = lines 

eachNthLetter ls n =[ [ x!!n | x <- ls]]

cycle' ls chars accum = 
  case chars of 
    [] -> accum;
    (c,n):cs -> cycle' ls cs accum ++ (eachNthLetter ls n)

mostOccurances letters = foldl1 (\a b -> if length a > length b then a else b) $ group $ sort letters

solution1 ls =  map (head . mostOccurances) $ reverse $ cycle' ls ( zip (head ls) [0..]) []

main = do
  contents <- getContents 
  let parsed = parser contents;
  print $ solution1 parsed 
 
  

