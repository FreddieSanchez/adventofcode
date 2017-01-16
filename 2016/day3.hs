import Data.List (sort)
parser :: String -> [[Integer]]
parser contents = [toInts (words w)  | w <-[ l |l <- lines contents]]
                  where 
                  toInts = map (\x -> read x :: Integer)

solution1:: [[Integer]] -> [[Integer]]
solution1 contents = filter isTriangle contents

isTriangle :: [Integer] -> Bool
isTriangle sides = let sorted = sort sides in 
                   (sum $ init sorted) > (last sorted)


solution2 contents =1
main = do
  contents <- getContents 
  let parsed = parser contents
  print $ length $ solution1 $ parsed
  print $ solution2 $ parsed
