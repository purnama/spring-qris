package id.purnama.qris;

import id.purnama.qris.object.QrisPayload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class QrisConfiguration implements WebMvcConfigurer {

    @Autowired
    private QrisHttpMessageConverter<QrisPayload> qrisHttpMessageConverter;

    @Autowired
    private QrisModelAttributeMethodProcessor qrisModelAttributeMethodProcessor;

    @Bean
    public QrisHttpMessageConverter<QrisPayload> qrisHttpMessageConverter(){
        return new QrisHttpMessageConverter<>();
    }

    @Bean
    public QrisModelAttributeMethodProcessor qrisModelAttributeMethodProcessor(){
        return new QrisModelAttributeMethodProcessor();
    }

    @Bean
    public QrisMapper qrisMapper(){
        return new QrisMapper();
    }

    @Bean
    public QrisParser qrisParser(){
        return new QrisParser();
    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(qrisHttpMessageConverter);
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(qrisModelAttributeMethodProcessor);
    }
}
