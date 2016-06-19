package diploma.queue;

import java.io.IOError;
import java.io.IOException;
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
        try {
            long numLines = Files.lines(path).count();
            Object[] strings = Files.lines(path).toArray();
            for (int i = 0; i < numLines; i++) {
                queue.add(strings[i].toString());
                Thread.sleep(5000);
            }
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
        catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }
}
