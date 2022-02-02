package xyz.euclia.jaqpotj.consumers;

import org.asynchttpclient.AsyncHttpClient;
import xyz.euclia.jaqpotj.models.Dataset;
import xyz.euclia.jaqpotj.serializer.Serializer;

import java.io.IOException;


public class DatasetConsumer extends BaseConsumer<Dataset>{

    private static final String DATASETENDPOINT = "/services/dataset/";

    public DatasetConsumer(AsyncHttpClient client, Serializer serializer, String basepath) {
        super(client, serializer, basepath + DATASETENDPOINT);
    }

    @Override
    public void close() throws IOException {

    }
}
