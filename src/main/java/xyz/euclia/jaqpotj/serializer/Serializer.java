package xyz.euclia.jaqpotj.serializer;

import java.io.Closeable;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Writer;
import java.util.Map;

public interface Serializer extends Closeable {

    public boolean isClosed();

    public void write(Object entity, OutputStream out);

    public void write(Object entity, Writer writer);

    public String write(Object entity);
//
    public <T> T parse(String content, Class<T> valueType);
//
    public <T> T parse(InputStream src, Class<T> valueType);
    public  <T> T parse(InputStream src, TypeReference<T> valueType);


}
