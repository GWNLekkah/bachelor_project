# GitIssueFinder

GitIssueFinder should be run with the following arguments:
```
INPUT_FILE_PATH GIT_LOCATION GIT_DIRECTORY ISSUE_PREFIX
```

## Example sets of arguments
The following arguments are used to analyze Apache Cassandra:
```
input/commitsCassandra.txt "C:/Program Files/Git/cmd/git.exe" "C:\Users\arjan\Documents\GitHub\cassandra" CASSANDRA
```

The following arguments are used to analyze Apache Hadoop:
```
input/commitsHadoop.txt "C:/Program Files/Git/cmd/git.exe" "C:\Users\arjan\Documents\GitHub\hadoop" HADOOP
```

The following arguments are used to analyze Apache Tajo:
```
input/commitsTajo.txt "C:/Program Files/Git/cmd/git.exe" "C:\Users\arjan\Documents\GitHub\tajo" TAJO
```

## Further explanation of the commands with examples

INPUT_FILE_PATH:
- path to the input file (e.g. ```./input.txt```)
- input file can be created by running the following command inside a Git repostiory: ```git rev-list --parents MAIN_BRANCH_NAME > input.txt```

GIT_LOCATION:
- location of the Git program (e.g. ```C:/ProgramFiles/Git/cmd/git.exe```)

GIT_DIRECTORY:
- directory of the Git repository (e.g. ```C:\Users\arjan\Documents\GitHub\cassandra```)

ISSUE_PREFIX:
- prefix of the issue tokens used in the issue tracking system (e.g. ```CASSANDRA```)
