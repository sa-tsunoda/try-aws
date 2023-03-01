package com.sample;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.sample.entity.user.User;
import io.smallrye.mutiny.Uni;
import io.vertx.mutiny.mysqlclient.MySQLPool;
import io.vertx.mutiny.sqlclient.Row;
import io.vertx.mutiny.sqlclient.RowIterator;
import io.vertx.mutiny.sqlclient.Tuple;

@Path("/user")
public class UserResource {
    @Inject
    MySQLPool client;

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Uni<User> hello(@PathParam("id") String id) {
        System.out.println("user select");
        return client
                .preparedQuery("select id,name from user where id = ?")
                .execute(Tuple.of(id))
                .onItem()
                .transform(rows -> {
                    RowIterator<Row> iterator = rows.iterator();
                    Row row = iterator.next();

                    return new User(row.getLong("id"),row.getString("name"));
                });
    }
}
