import java.io.*;
import java.util.ArrayList;

/**
 * Class that contains the main function.
 */
public class Main {
    /**
     * Print all changes in the dependencies in the POM files between commits of
     * a Git repository.
     * @param args program arguments
     */
    public static void main(String[] args) throws IOException, InterruptedException {
        WorkPool workPool = new WorkPool(args[0]);
        Arguments.setPomFileName(args[1]);
        Arguments.setGitProgramLocation(args[2]);
        Arguments.setRepositoryUrl(args[3]);
        Arguments.setRepositoryName(args[4]);
        Arguments.setFilter(args[5]);

        ArrayList<WorkerThread> threads = new ArrayList<>();
        int numOfThreads = (Runtime.getRuntime().availableProcessors() + 1) / 2;

        // create output folder
        File outputDirectory = new File("../output");
        outputDirectory.mkdirs();

        // notify user if same name
        String[] outputFiles = {"../output/diff.txt", "../output/diffAmounts.csv"};
        for (String outputFile: outputFiles) {
            File file = new File(outputFile);
            if (file.exists()) {
                System.err.println(outputFile + " already exists");
                return;
            }
        }

        // create tmp folder for working directories of the threads
        File tmpFile = new File("../tmp");
        tmpFile.mkdirs();

        // create and start threads
        for (int iter = 0; iter < numOfThreads; ++iter) {
            threads.add(new WorkerThread(workPool, "../tmp/" + Arguments.getRepositoryName() + iter));
            threads.get(iter).start();
        }

        // join threads
        for (int iter = 0; iter < numOfThreads; ++iter) {
            threads.get(iter).join();
        }

        Directory.deleteDirectory(tmpFile);
    }
}
