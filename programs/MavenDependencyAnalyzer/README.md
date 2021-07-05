# MavenDependencyAnalyzer

MavenDependencyAnalyzer analyzes Maven dependencies of a Git repository. The child commit's dependencies are compared with the parent commit's dependencies. Changes in the dependencies printed to the output files. The program should be given the following arguments:
```
INPUT_FILE_PATH DEPENDENCY_FILENAME GIT_LOCATION GIT_URL REPOSITORY_NAME FILTER
```

## Further explanation of the commands with examples

INPUT_FILE_PATH:
- This is the path to the input file. The input file is created by running the following command inside a Git repository: ```git rev-list --parents MAIN_BRANCH_NAME > input.txt```. A few sample input files can be found in the folder ```input_files```.

DEPENDENCY_FILENAME:
- This is the filename of the files that contain the Maven dependencies. Dependencies of Apache Cassandra are stored in a file called ```build.xml```. Dependencies of Apache Hadoop and Tajo are stored in files called ```pom.xml```.

GIT_LOCATION:
- This is the location of the command-line version of Git on the machine where the program is executed on. For a typical Windows machine this would be for example: ```C:/ProgramFiles/Git/cmd/git.exe```

GIT_URL:
- This is the URL of the Git repository. This program uses this URL for cloning the repository. The URL of Apache Cassandra is for example: ```https://github.com/apache/cassandra.git```

REPOSITORY_NAME:
- This is the name of the repository. For the three Apache project (Cassandra, Hadoop and Tajo) these names are: ```cassandra```, ```hadoop``` and ```tajo```

FILTER:
- This filter is used to skip dependencies that have a certain groupId. Providing the filter ```org.apache.cassandra``` would skip dependencies with ```org.apache.cassandra``` as the groupId. The empty string can be used when no dependencies have to be skipped.

## Example sets of arguments
The following arguments are used to analyze Apache Cassandra:
```
input/commitsCassandra.txt build.xml "C:/Program Files/Git/cmd/git.exe" https://github.com/apache/cassandra.git cassandra "org.apache.cassandra"
```

The following arguments are used to analyze Apache Hadoop:
```
input/commitsHadoop.txt pom.xml "C:/Program Files/Git/cmd/git.exe" https://github.com/apache/hadoop.git hadoop "org.apache.hadoop"
```

The following arguments are used to analyze Apache Tajo:
```
input/commitsTajo.txt pom.xml "C:/Program Files/Git/cmd/git.exe" https://github.com/apache/tajo.git tajo "org.apache.tajo"
```