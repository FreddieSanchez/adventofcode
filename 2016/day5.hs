import Data.Char (isDigit)
import Data.ByteString.Lazy.Char8 (pack)
import Data.Digest.Pure.MD5 (md5)
import Debug.Trace (trace)


doorMd5 :: Int -> String -> String
doorMd5 current doorId = show $ md5 $ pack $ doorId ++ show current


md5Seq ::  Int -> String -> [String]
--md5Seq a b | trace ("md5seq" ++ show a ++ " " ++ show b) False = undefined
md5Seq current doorId = doorMd5 current doorId : md5Seq (current + 1) doorId 

isInteresting :: String -> Bool
isInteresting s = take 5 s == "00000"

isValid :: String -> Bool
isValid s = let d = s!! 5 in 
          isDigit d &&  read [d] < 8

interestingSeq :: String -> [String]
interestingSeq str = filter isInteresting $ md5Seq 0 str

interestingValidSeq :: String -> [String]
interestingValidSeq str = filter (\s -> isInteresting s && isValid s) $ md5Seq 0 str

solution1 :: String -> String
solution1 str = take 8 [ hashes!!5 | hashes <- interestingSeq str]


replace :: String -> String -> String
replace hash solution = replacePos solution (hash!!6) (read [hash!!5]) 

replacePos:: String -> Char -> Int -> String
replacePos a c i | trace ("checkSolution " ++ show a  ++ show c ++ show i) False = undefined
replacePos hash hexDigit index 
 | index > length hash = error "To long"
 | otherwise = let digitAtIndex = hash!!index in 
    [if digitAtIndex == '-' && 
        i == index then hexDigit 
    else 
        c | (c,i) <- zip hash [0..]]


checkSolution :: String -> Bool
checkSolution a | trace ("checkSolution " ++ show a ) False = undefined
checkSolution str = all (/= '-') str 

findSolution2 str solution hashes = if checkSolution solution
                             then solution 
                             else 
                                findSolution2 str (replace (head hashes) solution) (tail hashes)

--solution2 :: String -> String 
solution2 str = findSolution2 str "--------" (interestingValidSeq str)

parser = takeWhile (/='\n')

main = do
  contents <- getContents 
  let parsed = parser contents;
  print $ solution1 parsed 
  print $ solution2 parsed 
 
  

