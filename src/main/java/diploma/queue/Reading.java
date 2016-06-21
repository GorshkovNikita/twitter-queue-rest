package diploma.queue;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

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
            String line = null;
            do {
                long start = System.currentTimeMillis();
                for (int i = 0; i < 1000; i++) {
                    line = br.readLine();
                    if (line != null)
                        queue.add(line);
                    else break;
                }
                long finish = System.currentTimeMillis() - start;
                Thread.sleep(1000 - finish);
            } while (line != null);
        }
        catch (InterruptedException | IOException ex) {
            ex.printStackTrace();
        }
    }
}
