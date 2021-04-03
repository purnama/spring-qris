package id.purnama.qris;

import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;

public class QrisConverter<T> extends AbstractHttpMessageConverter<T> {

    private final String name;
    private final Class<T> supportedClass;

    protected QrisConverter(MediaType mediaType, String name, Class<T> supportedClass) {
        super(mediaType);
        this.name = name;
        this.supportedClass = supportedClass;
    }


    @Override
    protected boolean supports(Class<?> aClass) {
        return supportedClass.isAssignableFrom(aClass);
    }

    @Override
    protected T readInternal(Class<? extends T> aClass, HttpInputMessage httpInputMessage) throws IOException, HttpMessageNotReadableException {
        String text = new BufferedReader(
                new InputStreamReader(httpInputMessage.getBody(), StandardCharsets.UTF_8))
                .lines()
                .collect(Collectors.joining("\n"));
        System.out.println(text);
        return null;
    }

    @Override
    protected void writeInternal(T t, HttpOutputMessage httpOutputMessage) throws IOException, HttpMessageNotWritableException {
        System.out.println(httpOutputMessage);
    }
}
