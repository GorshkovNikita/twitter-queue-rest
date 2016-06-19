package diploma.queue;

import javax.inject.Singleton;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.Path;
import java.nio.file.Paths;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by Nikita on 18.06.2016.
 */
@Singleton
@Path("/")
public class TwitterQueue {
    private BlockingQueue<String> tweets;

    public TwitterQueue() {
        this.tweets = new LinkedBlockingQueue<>();
        Thread readingThread = new Thread(new Reading(Paths.get("D:\\MSU\\diploma\\tweets.txt"), this.tweets));
        readingThread.setDaemon(true);
        readingThread.start();
    }

    @GET
    @Produces("application/json")
    public String getClichedMessage() {
        if (tweets.size() != 0)
            return tweets.poll();
        else
            return "{\"queue\": \"empty\"}";
    }
}
