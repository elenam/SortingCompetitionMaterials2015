# SortingCompetitionMaterials2015

Materials for UMM CSci 3501 "Algorithms and computability" 2015 sorting competition. 

## Goal of the competition

The Sorting Competition is a multi-lab exercise on developing the fastest sorting algorithm for a given type of data. 
By "fast" we mean the actual running time and not the Big-Theta approximation. The solutions are developed in Java 
and will be ran on a single processor.

## The data 
The data to be sorted consists of strings representing decimal numbers in the range [0, 1). 
Each number has 9 decimal places, padded with zeros as needed: `0.035648700`.

### How is it sorted
`n1` precedes `n2` in the ordering if and only if one of the following is true:
1. The sum of the first four digits (after the decimal point) of `n1` modulo 10 is **greater** than the sum of the first four digits of `n2` modulo 10. 
2. The sums of the first four digits of `n1` and `n2` are **equal** and the value of `n1` is **smaller** than the value of `n2`.

For instance: 
- `n1 = 0.13226783` precedes `n2 = 0.98985673` in the ordering because `1 + 3 + 2 + 2 = 8`, which is 8 modulo 10, `9 + 8 + 9 + 8 = 34` is `4` modulo 10, and `8 > 4`. 
- `n1 = 0.132278961` precedes `n2 = 0.532821341` because the sum of their first four digist both give 8 modulo 10, and  `0.132278961 < 0.532821341`. 
- `n1 = 0.132278961` precedes `n2 = 0.132278978` because their first four digits are exactly the same and `0.132278961 < 0.132278978`. This case is actually not different from the previous one. 

If `n1` and `n2` are exactly the same, they may appear in any order. 








### Data distribution




