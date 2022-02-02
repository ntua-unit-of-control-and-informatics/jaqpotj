package xyz.euclia.jaqpotj.consumers;


import org.asynchttpclient.AsyncHttpClient;
import xyz.euclia.jaqpotj.models.Task;
import xyz.euclia.jaqpotj.serializer.Serializer;

import java.io.IOException;

public class TaskConsumer extends BaseConsumer<Task>{


    private static final String TASKENDPOINT = "/services/task/";


    public TaskConsumer(AsyncHttpClient client, Serializer serializer, String basepath) {
        super(client, serializer, basepath + TASKENDPOINT);
    }

    @Override
    public void close() throws IOException {

    }
}
