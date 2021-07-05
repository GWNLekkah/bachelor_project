# parser

GitIssueFinder should be run with the following arguments:
```
python3 parser.py -i INPUT_FILE
```

## Further explanation of the commands with examples

INPUT_FILE:
- the input file is created by exporting Atlas.ti annotations to a CSV file. The required columns can be found in the provided input files (from the ```input_files``` folder)

## Example sets of arguments
The program can be run using the provided input files (from the ```input_files``` folder) using the following set of arguments:
```
python3 parser.py -i "input_structural.csv"
```
```
python3 parser.py -i "input_technology.csv"
```