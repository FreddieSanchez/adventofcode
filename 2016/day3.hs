import Data.List (sort)
parser :: String -> [[Integer]]
parser contents = [toInts (words w)  | w <-[ l |l <- lines contents]]
                  where 
                  toInts = map (\x -> read x :: Integer)

parser2 :: [[Integer]] -> [[Integer]]
parser2 [] = []
parser2 (x:y:z:xs) = (toList (zip3 x y z)) ++ parser2 xs
        where
        toList l = [[x,y,z] | (x,y,z) <- l]

solution1:: [[Integer]] -> [[Integer]]
solution1 contents = filter isTriangle

isTriangle :: [Integer] -> Bool
isTriangle sides = let sorted = sort sides in 
                   (sum $ init sorted) > (last sorted)

main = do
  contents <- getContents 
  let parsed = parser contents
  print $ length $ solution1 $ parsed
  print $ length $ solution1 $ parser2 parsed 
