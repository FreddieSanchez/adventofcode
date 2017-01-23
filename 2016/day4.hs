import Data.Char
import Data.List (sort, group, sortBy)

data Occurance = Occurance { letter::Char, times::Int} deriving (Show, Eq)

instance Ord Occurance where
  compare b1 b2   
    |  (times b1) == (times b2) = compare (letter b1) (letter b2)
    |  otherwise = compare (times b1) (times b2)


compare1 :: Occurance -> Occurance -> Ordering
compare1 b1 b2   
  |  (times b1) == (times b2) = compare (letter b1) (letter b2)
  |  otherwise = compare (times b2) (times b1)

lineParser :: String -> [String]
lineParser = lines


main = do
  contents <- getContents 
  let parsed  = lineParser contents in
    print $take 5 $ map letter $ sortBy compare1 $ map (\s -> Occurance {letter = (head s), times = ( length s)}) 
      (group $ sort $ takeWhile isLetter (head parsed))
 
