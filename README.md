# Regex-Hypernym-Database

## Introduction
This project consists of two parts:
Part 1: Construct a database of hypernym relations.
Part 2: Discover a hypernym using a lemma.

## Part 1
find and aggregate hypernym relations that 
match the Hearst patterns
using regular expressions, and save them in a txt file that will look like:**
```
hypernym: hyponym1 (x), hyponym2 (x) ...
hypernym: hyponym1 (x), hyponym2 (x) ...
...
```
**where (x) corresponds to the number of occurrences of the relations (across all possible patterns) in the corpus.**
For each hypernym, the list of co-hyponyms are sorted according to (x) in descending order, for example as follows:
```
country: united kingdom (10), israel (9), japan (9)
```

To use part 1 the program requires the input of two arguments:
```
(1) the path to the directory of the corpus and (2) the path to the output file.
```

## Part 2 
The functionality to search all the possible hypernyms of the input lemma and print them to the console as follows:**
```
hypernym1: (x)
hypernym2: (x)
...
```
**where (x) corresponds to the number of occurrences of the relations
(across all possible patterns) in the corpus.**
The hypernyms are sorted in a descending order according to (x).

To use part 2 the program requires the input of two arguments:
```
(1) the absolute path to the directory of the corpus and (2) a lemma.
```

## Example
This is a part of the text file output after processing a 52 GB corpus in 45 seconds using a home PC's computational resources.

![image](https://user-images.githubusercontent.com/62385332/203856631-f1e04dd5-792f-4546-a335-3196c6753459.png)
