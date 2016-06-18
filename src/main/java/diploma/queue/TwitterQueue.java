package diploma.queue;

import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.Path;

/**
 * Created by Nikita on 18.06.2016.
 */
@Path("/hello")
public class TwitterQueue {
    @GET
    @Produces("text/plain")
    public String getClichedMessage() {
        return "Hello World";
    }
}
