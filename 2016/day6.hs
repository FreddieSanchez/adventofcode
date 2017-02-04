import Data.List (group, sort, sortBy)

parser = lines 

transpose' :: [[a]] -> [[a]]
transpose' ([]:_) = []
-- get the head of each of the lists,
-- call transpose with the tail of all of the lists.
transpose' x = map head x : transpose' (map tail x)


occurances letters f = foldl1 (\a b -> if (f a b) then a else b) $ group $ sort letters

mostOccurances letters = occurances letters (\a b -> length a > length b )
leasetOccurances letters = occurances  letters (\a b -> length a < length b )

solution1 ls =  map (head . mostOccurances) $ transpose' ls
solution2 ls =  map (head . leasetOccurances) $ transpose' ls


main = do
  contents <- getContents 
  let parsed = parser contents;
  print $ solution1 parsed 
  print $ solution2 parsed 
 
  

