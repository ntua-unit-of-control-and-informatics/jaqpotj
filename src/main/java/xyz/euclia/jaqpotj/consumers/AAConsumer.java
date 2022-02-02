package xyz.euclia.jaqpotj.consumers;

import io.netty.handler.codec.http.HttpHeaders;
import org.asynchttpclient.*;
import xyz.euclia.jaqpotj.exception.JaqpotException;
import xyz.euclia.jaqpotj.models.Auth;
import xyz.euclia.jaqpotj.models.Task;
import xyz.euclia.jaqpotj.serializer.Serializer;
import xyz.euclia.jaqpotj.serializer.TypeReference;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.SequenceInputStream;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class AAConsumer extends BaseConsumer<Auth> {


    private static final String AAENDPOINT = "/services/aa/";
    private final AsyncHttpClient client;
    private final Serializer serializer;
    protected String path;
    protected String basePath;

    public AAConsumer(AsyncHttpClient client, Serializer serializer, String basepath) {
        super(client, serializer, basepath + AAENDPOINT);
        this.client = client;
        this.serializer = serializer;
        this.path = basepath + AAENDPOINT;
        this.basePath = basepath;
    }


    public CompletableFuture<Auth> login(String username, String password) throws JaqpotException {

        RequestBuilder postRequest = new RequestBuilder();
        postRequest.setUrl(this.path + "login");
        postRequest.setHeader("accept", "application/json");
        postRequest.setHeader("Content-Type", "application/x-www-form-urlencoded");
//        String dataset_uri = this.basePath + "/services/dataset/" + datasetUri;

        List<Param> params = new ArrayList<>();
        Param p = new Param("username", username);
        Param p2 = new Param("password", password);
        params.add(p);
        params.add(p2);
        postRequest.setFormParams(params);

        postRequest.setMethod("POST");
        Request req = postRequest.build();

        return this.client.executeRequest(req, new AsyncHandler<Auth>() {

            private InputStream sis;

            TypeReference<Auth> t =new TypeReference<Auth>() {
                @Override
                public Type getType() {
                    return super.getType();
                }
            };

            @Override
            public State onStatusReceived(HttpResponseStatus responseStatus) throws JaqpotException {
                int statusCode = responseStatus.getStatusCode();
                if (statusCode >= 400) {
                    throw new JaqpotException(responseStatus.getStatusText());
                }
                return AsyncHandler.State.CONTINUE;
            }

            @Override
            public State onHeadersReceived(HttpHeaders headers) throws Exception {
                return AsyncHandler.State.CONTINUE;
            }

            @Override
            public State onBodyPartReceived(HttpResponseBodyPart bodyPart) throws Exception {
                if (sis == null) {
                    sis = new ByteArrayInputStream(bodyPart.getBodyPartBytes(), 0, bodyPart.length());
                } else {
                    sis = new SequenceInputStream(sis, new ByteArrayInputStream(bodyPart.getBodyPartBytes(), 0, bodyPart.length()));
                }
                return AsyncHandler.State.CONTINUE;
            }

            @Override
            public void onThrowable(Throwable t) {
                throw new JaqpotException(t.getMessage(), t.getCause(), true, true);
            }

            @Override
            public Auth onCompleted() throws Exception {
                return serializer.parse(sis,t);
            }
        }).toCompletableFuture();

    }


    @Override
    public void close() throws IOException {

    }


}
