package xyz.euclia.jaqpotj.consumers;

import org.asynchttpclient.AsyncHttpClient;
import xyz.euclia.jaqpotj.models.Feature;
import xyz.euclia.jaqpotj.serializer.Serializer;

import java.io.IOException;

public class FeatureConsumer extends BaseConsumer<Feature> {

    private static final String FEATUREENDPOINT = "/jaqpot/services/feature/";

    public FeatureConsumer(AsyncHttpClient client, Serializer serializer, String basepath) {
        super(client, serializer, basepath + FEATUREENDPOINT);
    }

    @Override
    public void close() throws IOException {

    }

}
