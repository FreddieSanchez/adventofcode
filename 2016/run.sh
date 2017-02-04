#!/bin/sh

read DAY;
while inotifywait -e close_write $DAY.hs; do 
  rm $DAY;
  echo "making!" 
  ghc --make $DAY.hs; 
  echo "Running Test Input!" 
  ./$DAY < $DAY.test; 
  echo "Running Real Input!" 
  ./$DAY < $DAY.input; done
