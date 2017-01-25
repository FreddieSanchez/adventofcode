import Data.Char
import Data.List (sort, group, sortBy)

data Room = Room { name ::String, sectorId::Integer, checksum::String} deriving (Show)

data Occurance = Occurance { letter::Char, times::Int} deriving (Show, Eq)

instance Ord Occurance where
  compare b1 b2   
    |  times b1 == times b2 = compare (letter b1) (letter b2)
    |  otherwise = compare (times b1) (times b2)


compare1 :: Occurance -> Occurance -> Ordering
compare1 b1 b2   
  |  times b1 == times b2 = compare (letter b1) (letter b2)
  |  otherwise = compare (times b2) (times b1)

roomParser :: String -> Room
roomParser s = Room {name     = takeWhile (not . isDigit) s, 
                     sectorId = read (filter isDigit s)::Integer,
                     checksum = filter isAlpha $ dropWhile (/='[') s }


computeChecksum :: Room -> String
computeChecksum room = let sorted = group $ filter isAlpha $ sort (name room)
                        in
                        take 5 $ map letter $ sortBy compare1 $ map genOccurance sorted
                          where 
                          genOccurance s = Occurance {letter = head s, times = length s}

                       
                       
                      
isValid :: Room -> Bool
isValid r = checksum r == computeChecksum r

solution1 :: [Room] -> Integer
solution1 rooms = foldl (\a b -> a + sectorId b) 0 $ filter isValid rooms


main = do
  contents <- getContents 
  let parsed  = map roomParser $ lines contents in
     print $  solution1 parsed
 
