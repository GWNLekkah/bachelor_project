import java.io.*;

/**
 * Class that retrieves work from a WorkPool and processes it.
 */
public class Worker {
    private final WorkPool workPool;

    public Worker(WorkPool workPool) {
        this.workPool = workPool;
    }

    /**
     * Runs a shell command to retrieve the Git message that belongs to the given commitHash.
     * @param commitHash hash of the commit
     * @return BufferedReader that can read the message
     */
    private BufferedReader getGitMessageReader(String commitHash) throws InterruptedException, IOException {
        // start shell command and wait until it is done
        Process process = Runtime.getRuntime().exec(
                Arguments.getGitProgramLocation() + " -C \"" +
                        Arguments.getGitRepositoryLocation() + "\" log --format=%B -n 1 " + commitHash
        );
        process.waitFor();

        // create input stream to get the output of the shell command
        InputStream inputStream = process.getInputStream();
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        return new BufferedReader(inputStreamReader);
    }

    /**
     * Splits the Git message by the issue name and returns the corresponding String array.
     * @param bufferedReader reader that can read the message
     * @return String array of the split message
     */
    private String[] messageToStringArr(String prefix, BufferedReader bufferedReader) throws IOException {
        // convert commit message to a StringBuilder
        StringBuilder result = new StringBuilder();
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            result.append(line);
        }

        // split the String at each issue name
        return result.toString().split(prefix + "-");
    }

    /**
     * Prints the issue names and numbers that were found in the Git message.
     * @param arr String array that was obtained by splitting the Git message by the issue name
     * @param bufferedWriter output is written to this
     */
    private void printIssues(String[] arr, String prefix, BufferedWriter bufferedWriter) throws IOException {
        // for each split, print the issue name and number
        for (int iter = 1; iter < arr.length; ++iter) {
            // write the issue name to the bufferedWriter
            bufferedWriter.write("    " + prefix + "-");

            // write the issue number to the bufferedWriter
            for (int idx = 0; idx < arr[iter].length(); ++idx) {
                char ch = arr[iter].charAt(idx);
                if (!Character.isDigit(ch)) {
                    // end of issue number
                    break;
                }
                bufferedWriter.write(ch);
            }
            bufferedWriter.write('\n');
        }
    }

    /**
     * Function that retrieves work from the WorkPool
     * until it is empty.
     */
    public void work() throws InterruptedException, IOException {
        // retrieve work from the WorkPool each iteration
        String work;
        while ((work = this.workPool.getWork()) != null) {
            // split work into commits
            String[] commits = work.split(" ");
            if (commits.length != 2) {
                // wrong amount of commits
                continue;
            }

            // get child commit
            String child = commits[0];

            // print commit hash to inform user that this commit is being processed
            System.out.println(child);

            // create writer to write to the output file
            FileWriter fileWriter = new FileWriter(Arguments.getOutputFile(), true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            // write the commit hash and print issues of each prefix
            bufferedWriter.write(child + "\n");
            for (String prefix : Arguments.getIssuePrefixes()) {
                BufferedReader bufferedReader = getGitMessageReader(child);
                printIssues(messageToStringArr(prefix, bufferedReader), prefix, bufferedWriter);
            }

            bufferedWriter.close();
        }
    }
}
