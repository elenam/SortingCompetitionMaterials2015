# SortingCompetitionMaterials2015

Materials for UMM CSci 3501 "Algorithms and computability" 2015 sorting competition. 

## Goal of the competition

The Sorting Competition is a multi-lab exercise on developing the fastest sorting algorithm for a given type of data. 
By "fast" we mean the actual running time and not the Big-Theta approximation. The solutions are developed in Java 
and will be ran on a single processor.

## The data 
The data to be sorted consists of strings representing decimal numbers in the range [0, 1). 
Each number has 9 decimal places, padded with zeros as needed: `0.035648700`.
The number of elements to be sorted will be between 500000 and 5000000.

### How is it sorted
`n1` precedes `n2` in the ordering if and only if one of the following is true:

1. The sum of the first four digits (after the decimal point) of `n1` modulo 10 is **greater** than the sum of the first four digits of `n2` modulo 10. 
2. The sums of the first four digits of `n1` and `n2` are **equal** and the value of `n1` is **smaller** than the value of `n2`.

For instance: 
- `n1 = 0.13226783` precedes `n2 = 0.98985673` in the ordering because `1 + 3 + 2 + 2 = 8`, which is 8 when taken modulo 10, `9 + 8 + 9 + 8 = 34` is `4` modulo 10, and `8 > 4`. 
- `n1 = 0.132278961` precedes `n2 = 0.532821341` because the sum of their first four digist both equal 8 when taken modulo 10, and  `0.132278961 < 0.532821341`. 
- `n1 = 0.132278961` precedes `n2 = 0.132278978` because their first four digits are exactly the same and `0.132278961 < 0.132278978`. This case is actually not different from the previous one. 

If `n1` and `n2` are exactly the same, they may appear in any order. 

The file [Group0.java](https://github.com/elenam/SortingCompetitionMaterials2015/blob/master/src/Group0.java) gives a reference implementation of sorting. It uses Java `Comparator` to provide the comparision of two strings according to the above criterion and passes the data to `Arrays.sort()`. The sorted data is written to a file. Note that this implementation will be used to check the correctness of your sorting, i.e. your sorting must produce an identical result. 

### Data distribution

The elements are generated in clusters in the range [0, 1). There will be 99 clusters that start at randomly chosen position in the range. The start position is chosen according to a random distribution (a position that is too close to the end of the data range for the cluster to fit is discarded and a new one is chosen). Each cluster contains n/99 elements (with some rounding).  

2/3 of the clusters are "wide" which means that they are generated with the maximum range of n/10 data, and 1/3 of the clusters are "narrow", generated with the maximum range of n/1000 elements. 

Details of the data generating process are in the  [DataGenerator2015.java](https://github.com/elenam/SortingCompetitionMaterials2015/blob/master/src/DataGenerator2015.java). 

There are 3 sample data files with the corresponding outputs:

| Input file | Output file | Number of elements |
|------------|-------------|--------------------|
| [sample3.txt](https://github.com/elenam/SortingCompetitionMaterials2015/blob/master/sample3.txt)| [out3.txt](https://github.com/elenam/SortingCompetitionMaterials2015/blob/master/out3.txt)    | 500000             | 
| [sample4.txt](https://github.com/elenam/SortingCompetitionMaterials2015/blob/master/sample4.txt)| [out4.txt](https://github.com/elenam/SortingCompetitionMaterials2015/blob/master/out4.txt)    | 2000000            |
| [sample5.txt](https://github.com/elenam/SortingCompetitionMaterials2015/blob/master/sample5.txt)| [out5.txt](https://github.com/elenam/SortingCompetitionMaterials2015/blob/master/out5.txt)    | 5000000            |

There are also data files [old-smaple1.txt](https://github.com/elenam/SortingCompetitionMaterials2015/blob/master/old-smaple1.txt) and [old-smaple2.txt](https://github.com/elenam/SortingCompetitionMaterials2015/blob/master/old-smaple2.txt) that were generated using a slightly different data distribution: it had an equal number of wide and narrow clusters. The correspomnding output files are [old-out1.txt](https://github.com/elenam/SortingCompetitionMaterials2015/blob/master/old-out1.txt) and [old-out2.txt](https://github.com/elenam/SortingCompetitionMaterials2015/blob/master/old-out2.txt). 





