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
- `n1 = 0.132267835` precedes `n2 = 0.989856735` in the ordering because `1 + 3 + 2 + 2 = 8`, which is 8 when taken modulo 10, `9 + 8 + 9 + 8 = 34` is `4` modulo 10, and `8 > 4`. 
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

### Setup for sorting

The file [Group0.java](https://github.com/elenam/SortingCompetitionMaterials2015/blob/master/src/Group0.java) provides a template for the setup for your solution. Your class will be called `GroupN', where `N` is the group number that will be assigned to your group. The template class runs the sorting method once before the timing for the [JVM warmup](http://alexandru-ersenie.com/2010/09/12/important-aspects-in-load-performance-testing-1-server-warm-up/). It also pauses for 10ms before the actual test to let any leftover I/O to finish. Since the warmup and the actual sorting are done on the same array (for no reason other than simplicity), the array is cloned from the same input data. 

The Data reading, the array cloning, the warmup sorting, and writing out the output are all outside of the timed portion of the method, and thus do not affect the total time. 

The only method in the [Group0.java](https://github.com/elenam/SortingCompetitionMaterials2015/blob/master/src/Group0.java) files that you may modify is the `sort` method (although it still needs to take the arary of strings). Once the method finishes, the `toSort` array must contain the sorted data as strings. The data is then written to a file. The correctness check will perform the `diff` on the output of your algorithm and the one provided by the reference sorting implementation (the one in the Group0.java file). 

Even though you are not modifying anything other than the `sort` method, you still need to submit your entire class: copy the template, rename the Java class to your group number, and change the`sort` method. You may use supplementary classes, just don't forget to submit them. Make sure to add your names in comments when you submit. 

### Dates:

*Friday, Oct 9* in class (1pm) is the *preliminary* competition. Please send me all your materials no later than 10:30am on Friday. This is required for everyone in the class. Groups remain anonymous after this phase, but all the solutions (in bytecode) become available. 

We may have a second preliminary competition some time the week of Oct 12. 

*Thursday, Oct 22* in the lab (2pm) is the *final* competition. All source code is posted immediately after that. Those in class will have their names revealed, others may choose to remain anonymous (but the code will still be posted). 

Note that there are several more parts of the Algorithms assignment, including presentations and a write-up. Obviously, these are only for students in the class. 

### System specs

The language used is Java 8 (as installed in the CSci lab). It's ran on a single CPU core.  

