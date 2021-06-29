import java.io.*;
import java.util.LinkedList;

/**
 * Class that contains lines of work that can be retrieved
 * by a Worker.
 */
public class WorkPool {
    private LinkedList<String> workPool;

    public WorkPool(String filename) throws IOException {
        createWorkPool(filename);
    }

    /**
     * Read the lines from a file and put them in a LinkedList.
     * @param filename file to read the lines from
     */
    private void createWorkPool(String filename) throws IOException {
        File inputFile = new File(filename);
        FileReader fileReader = new FileReader(inputFile);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String line;
        this.workPool = new LinkedList<>();

        // put lines from file in the WorkPool
        while ((line = bufferedReader.readLine()) != null) {
            this.workPool.push(line);
        }
    }

    /**
     * Function to get work from the WorkPool.
     * @return a line of work
     */
    public String getWork() {
        if (this.workPool.size() == 0) {
            // no more work left
            return null;
        }
        return this.workPool.pop();
    }
}
