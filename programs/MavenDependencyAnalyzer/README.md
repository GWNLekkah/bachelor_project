# MavenDependencyAnalyzer

MavenDependencyAnalyzer should be run with the following arguments:
```
INPUT_FILE_PATH DEPENDENCY_FILENAME GIT_LOCATION GIT_URL REPOSITORY_NAME FILTER
```

As an example, the following arguments are used to analyze Apache Cassandra:
```
input/commitsCassandra.txt build.xml "C:/Program Files/Git/cmd/git.exe" https://github.com/apache/cassandra.git cassandra "org.apache.cassandra"
```

## Further explanation of the commands with examples

INPUT_FILE_PATH:
- path to the input file (e.g. ```./input.txt```)
- input file can be created by running the following command inside a Git repostiory: ```git rev-list --parents MAIN_BRANCH_NAME > input.txt```

DEPENDENCY_FILENAME:
- name of the files containing dependencies (e.g. ```pom.xml``` or ```build.xml```)

GIT_LOCATION:
- location of the Git program (e.g. ```C:/ProgramFiles/Git/cmd/git.exe```)

GIT_URL:
- URL of the Git repository (e.g. ```https://github.com/apache/cassandra.git```)

REPOSITORY_NAME:
- name of the repository (e.g. ```cassandra``` or ```hadoop```)

FILTER:
- skip dependencies with FILTER as the groupId (e.g. ```org.apache.cassandra```)