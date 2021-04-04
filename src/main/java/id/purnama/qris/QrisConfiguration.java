package id.purnama.qris;

import id.purnama.qris.object.QrisPayload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class QrisConfiguration implements WebMvcConfigurer {
    private static final MediaType QRIS_VALUE = MediaType.valueOf("application/qris");

    @Autowired
    private id.purnama.qris.QrisStringToPayloadConverter qrisStringToPayloadConverter;

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(new QrisConverter<>(QRIS_VALUE, "qris object converter", QrisPayload.class));
    }

    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
        configurer.mediaType(QRIS_VALUE.getSubtype(), QRIS_VALUE);
    }
    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(this.qrisStringToPayloadConverter);
    }
}
