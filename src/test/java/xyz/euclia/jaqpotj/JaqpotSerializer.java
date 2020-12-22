package xyz.euclia.jaqpotj;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.module.jaxb.JaxbAnnotationIntrospector;
import org.junit.Test;
import xyz.euclia.jaqpotj.serializer.Serializer;
import xyz.euclia.jaqpotj.serializer.TypeReference;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Writer;
import java.lang.reflect.Type;


public class JaqpotSerializer implements Serializer {

    private final ObjectMapper objectMapper;

    JaqpotSerializer(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public boolean isClosed() {
        return false;
    }

    @Override
    public void write(Object entity, OutputStream out) {
        try {
            objectMapper.writeValue(out, entity);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public void write(Object entity, Writer writer) {
        try {
            objectMapper.writeValue(writer, entity);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public String write(Object entity) {
        try {
            return objectMapper.writeValueAsString(entity);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public <T> T parse(String content, Class<T> valueType) {
        try {
            return objectMapper.readValue(content, valueType);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public <T> T parse(InputStream src, Class<T> valueType) {
        try {
            return objectMapper.readValue(src, valueType);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public <T> T parse(InputStream src, TypeReference<T> valueType) {
//        return null;
        try {
            return objectMapper.readValue(src, new com.fasterxml.jackson.core.type.TypeReference<T>() {
                @Override
                public Type getType() {
                    return valueType.getType();
                }

            });
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public void close() throws IOException {

    }
}
