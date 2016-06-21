package diploma.queue;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.concurrent.BlockingQueue;

/**
 * Created by Nikita on 19.06.2016.
 */
public class Reading implements Runnable {
    private Path path;
    private BlockingQueue<String> queue;

    public Reading(Path path, BlockingQueue<String> queue) {
        this.path = path;
        this.queue = queue;
    }

    public void run() {
        try (BufferedReader br = new BufferedReader(new FileReader(path.toString()))) {
            String line;
            while ((line = br.readLine()) != null) {
                queue.add(line);
                Thread.sleep(1);
            }
        }
        catch (InterruptedException | IOException ex) {
            ex.printStackTrace();
        }
    }
}
