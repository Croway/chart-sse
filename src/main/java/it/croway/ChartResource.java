package it.croway;

import java.util.Random;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import io.smallrye.mutiny.Multi;
import io.vertx.mutiny.core.Vertx;

@Path("chart")
public class ChartResource {

    @Inject
    Vertx vertx;

    Random random = new Random();

    @GET
    @Path("data")
    @Produces(MediaType.SERVER_SENT_EVENTS)
    public Multi<Integer> getMessage() {
        return vertx.periodicStream(2000).toMulti()
                .map(l -> {
                    return random.nextInt(200);
                });
    }

}