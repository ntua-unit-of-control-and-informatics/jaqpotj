package xyz.euclia.jaqpotj;

import org.asynchttpclient.AsyncHttpClient;
import org.asynchttpclient.DefaultAsyncHttpClient;
import xyz.euclia.jaqpotj.consumers.DatasetConsumer;
import xyz.euclia.jaqpotj.consumers.FeatureConsumer;
import xyz.euclia.jaqpotj.consumers.ModelConsumer;
import xyz.euclia.jaqpotj.consumers.TaskConsumer;
import xyz.euclia.jaqpotj.serializer.Serializer;

public class JaqpotFactory {

    public Jaqpot create(String basepath, Serializer serializer){
        AsyncHttpClient httpClient = new DefaultAsyncHttpClient();
        return new JaqpotImplementation(httpClient,
                        serializer,
                        new ModelConsumer(httpClient, serializer, basepath),
                        new FeatureConsumer(httpClient, serializer, basepath),
                        new DatasetConsumer(httpClient, serializer, basepath),
                        new TaskConsumer(httpClient, serializer, basepath));

    }

}
