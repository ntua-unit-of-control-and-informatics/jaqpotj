package xyz.euclia.jaqpotj.consumers;

import io.netty.handler.codec.http.HttpHeaders;
import org.asynchttpclient.*;
import org.asynchttpclient.request.body.multipart.Part;
import org.asynchttpclient.request.body.multipart.StringPart;
import xyz.euclia.jaqpotj.exception.JaqpotException;
import xyz.euclia.jaqpotj.models.Dataset;
import xyz.euclia.jaqpotj.models.Model;
import xyz.euclia.jaqpotj.models.Models;
import xyz.euclia.jaqpotj.models.Task;
import xyz.euclia.jaqpotj.serializer.Serializer;
import xyz.euclia.jaqpotj.serializer.TypeReference;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.SequenceInputStream;
import java.lang.reflect.Type;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ModelConsumer extends BaseConsumer<Model>{

    private static final String MODELENDPOINT = "/services/model/";
    private final AsyncHttpClient client;
    private final Serializer serializer;
    protected String path;
    protected String basePath;

    public ModelConsumer(AsyncHttpClient client, Serializer serializer, String basepath) {
        super(client, serializer, basepath + MODELENDPOINT);
        this.client = client;
        this.serializer = serializer;
        this.path = basepath + MODELENDPOINT;
        this.basePath = basepath;
    }

    public CompletableFuture<Models> getModelsWithParams(List<Param> params, String token) throws JaqpotException {

        RequestBuilder getRequest = new RequestBuilder();
        getRequest.setUrl(this.path);
        getRequest.setQueryParams(params);
        getRequest.setHeader("Authorization", "Bearer " + token);
        Request req = getRequest.build();

        return this.client.executeRequest(req, new AsyncHandler<Models>() {

            private InputStream sis;
            private HttpHeaders headersGot;

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
                this.headersGot = headers;
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
            public Models onCompleted() throws Exception {
                TypeReference<List<Model>> c =new TypeReference<List<Model>>() {
                    @Override
                    public Type getType() {
                        return super.getType();
                    }
                };
                List<Model> ms = serializer.parse(sis,c);
                Models models = new Models();
                models.setModels(ms);
                models.setTotal(Integer.parseInt(this.headersGot.get("total")));
                return models;
            }
        }).toCompletableFuture();
    }

    public CompletableFuture<Task> predict(String modelId, String datasetUri, String token) throws JaqpotException {

        RequestBuilder postRequest = new RequestBuilder();
        postRequest.setUrl(this.path + modelId);
        postRequest.setHeader("Authorization", "Bearer " + token);
        postRequest.setHeader("accept", "application/json");
        postRequest.setHeader("Content-Type", "application/x-www-form-urlencoded");
        String dataset_uri = this.basePath + "/services/dataset/" + datasetUri;

        List<Param> params = new ArrayList<>();
        Param p = new Param("dataset_uri", dataset_uri);
        params.add(p);
        postRequest.setFormParams(params);

        postRequest.setMethod("POST");
        Request req = postRequest.build();

        return this.client.executeRequest(req, new AsyncHandler<Task>() {

            private InputStream sis;

            TypeReference<Task> t =new TypeReference<Task>() {
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
            public Task onCompleted() throws Exception {
                return serializer.parse(sis,t);
            }
        }).toCompletableFuture();
    }


    @Override
    public void close() throws IOException {

    }
}
