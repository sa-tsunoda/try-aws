package com.sample;

import com.sample.entity.user.User;
import com.sample.s3.S3Logic;
import io.smallrye.mutiny.Uni;
import io.vertx.mutiny.sqlclient.Row;
import io.vertx.mutiny.sqlclient.RowIterator;
import io.vertx.mutiny.sqlclient.Tuple;

import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/file")
public class FileResource {

    @Path("/upload")
    @POST
    @Transactional
    public String upload() {
        System.out.println("file upload start!");
        try {
            S3Logic.upload("test.txt");
        }catch(Exception e){
            System.out.println(e);
            return "error";
        }
        return "done";
    }
}
