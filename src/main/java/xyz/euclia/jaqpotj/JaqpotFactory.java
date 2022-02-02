package xyz.euclia.jaqpotj;

import org.asynchttpclient.AsyncHttpClient;
import org.asynchttpclient.DefaultAsyncHttpClient;
import xyz.euclia.jaqpotj.consumers.*;
import xyz.euclia.jaqpotj.serializer.Serializer;

public class JaqpotFactory {

    public Jaqpot create(String basepath, Serializer serializer){
        AsyncHttpClient httpClient = new DefaultAsyncHttpClient();
        return new JaqpotImplementation(httpClient,
                        serializer,
                        new AAConsumer(httpClient, serializer, basepath),
                        new ModelConsumer(httpClient, serializer, basepath),
                        new FeatureConsumer(httpClient, serializer, basepath),
                        new DatasetConsumer(httpClient, serializer, basepath),
                        new TaskConsumer(httpClient, serializer, basepath));
    }

}
