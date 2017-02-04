import Data.List (group, sort, sortBy)

parser = lines 

eachNthLetter ls n =[ [ x!!n | x <- ls]]

cycle' ls chars accum = 
  case chars of 
    [] -> accum;
    (c,n):cs -> cycle' ls cs accum ++ (eachNthLetter ls n)

occurances letters f = foldl1 (\a b -> if (f a b) then a else b) $ group $ sort letters

mostOccurances letters = occurances letters (\a b -> length a > length b )
leasetOccurances letters = occurances  letters (\a b -> length a < length b )

solution1 ls =  map (head . mostOccurances) $ reverse $ cycle' ls ( zip (head ls) [0..]) []
solution2 ls =  map (head . leasetOccurances) $ reverse $ cycle' ls ( zip (head ls) [0..]) []


main = do
  contents <- getContents 
  let parsed = parser contents;
  print $ solution1 parsed 
  print $ solution2 parsed 
 
  

