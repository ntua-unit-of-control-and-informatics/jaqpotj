package xyz.euclia.jaqpotj.consumers;

import io.netty.handler.codec.http.HttpHeaders;
import org.asynchttpclient.*;
import org.asynchttpclient.util.HttpConstants;
import xyz.euclia.jaqpotj.exception.JaqpotException;
import xyz.euclia.jaqpotj.models.JaqpotEntity;
import xyz.euclia.jaqpotj.models.Model;
import xyz.euclia.jaqpotj.serializer.Serializer;
import xyz.euclia.jaqpotj.serializer.TypeReference;

import java.io.ByteArrayInputStream;
import java.io.Closeable;
import java.io.InputStream;
import java.io.SequenceInputStream;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

/**
 *
 * @author pantelispanka
 */
public abstract  class BaseConsumer <T> implements Closeable {

    private final AsyncHttpClient client;
    private final Serializer serializer;
    protected String path;

    BaseConsumer(AsyncHttpClient client, Serializer serializer, String path) {
        this.client = client;
        this.serializer = serializer;
        this.path = path;
    }

    public <T> CompletableFuture<T> getList(List<Param> params, String token, TypeReference<T> c) throws JaqpotException {

        RequestBuilder getRequest = new RequestBuilder();
        getRequest.setUrl(this.path);
        getRequest.setQueryParams(params);
        getRequest.setHeader("Authorization", "Bearer " + token);
        getRequest.setHeader("accept", "application/json");
        Request req = getRequest.build();

        return this.client.executeRequest(req, new AsyncHandler<T>() {

            private InputStream sis;

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
            public T onCompleted() throws Exception {
                return serializer.parse(sis,c);
            }
        }).toCompletableFuture();
    }

    public <T> CompletableFuture<T> getWithId(String id, String token, TypeReference<T> c) throws JaqpotException {

        RequestBuilder getRequest = new RequestBuilder();
        getRequest.setUrl(this.path + id);
        getRequest.setHeader("Authorization", "Bearer " + token);
        getRequest.setHeader("accept", "application/json");
        Request req = getRequest.build();

        return this.client.executeRequest(req, new AsyncHandler<T>() {

            private InputStream sis;

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
            public T onCompleted() throws Exception {
                return serializer.parse(sis,c);
            }
        }).toCompletableFuture();
    }

    public <T> CompletableFuture<T> getWithIdAndParams(String id, List<Param> params, String token, TypeReference<T> c) throws JaqpotException {

        RequestBuilder getRequest = new RequestBuilder();
        getRequest.setUrl(this.path + id);
        getRequest.setQueryParams(params);
        getRequest.setHeader("Authorization", "Bearer " + token);
        getRequest.setHeader("accept", "application/json");
        Request req = getRequest.build();

        return this.client.executeRequest(req, new AsyncHandler<T>() {

            private InputStream sis;

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
            public T onCompleted() throws Exception {
                return serializer.parse(sis,c);
            }
        }).toCompletableFuture();
    }


    public <T> CompletableFuture<T> getWithParams(List<Param> params, String token, TypeReference<T> c) throws JaqpotException {

        RequestBuilder getRequest = new RequestBuilder();
        getRequest.setUrl(this.path);
        getRequest.setQueryParams(params);
        getRequest.setHeader("Authorization", "Bearer " + token);
        getRequest.setHeader("accept", "application/json");
        Request req = getRequest.build();

        return this.client.executeRequest(req, new AsyncHandler<T>() {

            private InputStream sis;

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
            public T onCompleted() throws Exception {
                return serializer.parse(sis,c);
            }
        }).toCompletableFuture();
    }

    public <T> CompletableFuture<T> post(String data, String token, TypeReference<T> c) throws JaqpotException {

        RequestBuilder postRequest = new RequestBuilder();
        postRequest.setUrl(this.path);
        postRequest.setHeader("Authorization", "Bearer " + token);
        postRequest.setHeader("accept", "application/json");
        postRequest.setHeader("Content-Type", "application/json");
//        postRequest.setBody(serializer.write(o));
        postRequest.setBody(data);
        postRequest.setMethod("POST");
        Request req = postRequest.build();

        return this.client.executeRequest(req, new AsyncHandler<T>() {

            private InputStream sis;

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
            public T onCompleted() throws Exception {
                return serializer.parse(sis,c);
            }
        }).toCompletableFuture();
    }

}
