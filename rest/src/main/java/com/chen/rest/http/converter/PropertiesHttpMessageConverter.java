package com.chen.rest.http.converter;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractGenericHttpMessageConverter;

import java.io.*;
import java.lang.reflect.Type;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

/**
 * PropertiesHttpMessageConverter
 * <p>
 * @Author LeifChen
 * @Date 2020-11-23
 */
public class PropertiesHttpMessageConverter extends AbstractGenericHttpMessageConverter<Properties> {

    public PropertiesHttpMessageConverter() {
        // 设置支持的 MediaType
        super(new MediaType("text", "properties"));
    }

    @Override
    protected void writeInternal(Properties properties, Type type, HttpOutputMessage httpOutputMessage) throws IOException {
        HttpHeaders httpHeaders = httpOutputMessage.getHeaders();
        Charset charset = getCharset(httpHeaders);

        OutputStream outputStream = httpOutputMessage.getBody();
        OutputStreamWriter writer = new OutputStreamWriter(outputStream, charset);
        properties.store(writer, "From PropertiesHttpMessageConverter");
    }

    @Override
    protected Properties readInternal(Class<? extends Properties> aClass, HttpInputMessage httpInputMessage) throws IOException {
        HttpHeaders httpHeaders = httpInputMessage.getHeaders();
        Charset charset = getCharset(httpHeaders);

        // 加载字符流成为 Properties 对象
        InputStream inputStream = httpInputMessage.getBody();
        InputStreamReader reader = new InputStreamReader(inputStream, charset);
        Properties properties = new Properties();
        properties.load(reader);
        return properties;
    }

    @Override
    public Properties read(Type type, Class<?> aClass, HttpInputMessage httpInputMessage) throws IOException {
        return readInternal(null, httpInputMessage);
    }

    /**
     * 从请求头 Content-Type 解析字符编码
     * @param httpHeaders 请求头信息
     * @return 返回字符编码
     */
    private Charset getCharset(HttpHeaders httpHeaders) {
        MediaType mediaType = httpHeaders.getContentType();
        assert mediaType != null;
        Charset charset = mediaType.getCharset();
        charset = charset == null ? StandardCharsets.UTF_8 : charset;
        return charset;
    }
}
