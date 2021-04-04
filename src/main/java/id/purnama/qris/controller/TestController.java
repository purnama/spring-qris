package id.purnama.qris.controller;

import id.purnama.qris.object.Qris;
import id.purnama.qris.object.QrisPayload;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;

import javax.validation.Valid;
import javax.validation.Validator;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
public class TestController {

    @Autowired
    private List<WebMvcConfigurer> configurers;
    @Autowired
    private List<HttpMessageConverter<?>> converters;
    @Autowired
    private RequestMappingHandlerAdapter requestMappingHandlerAdapter;
    @Autowired
    private Validator validator;

    @GetMapping(value = "/qris", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public QrisPayload parseMap(@Valid QrisPayload payload){
        return payload;
    }

    @GetMapping(value = "/qris/object", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public Qris parseObject(Qris payload){
        return payload;
    }

    @PostMapping(value = "/qris", consumes = "application/qris", produces = {"application/qris", "application/json"})
    public QrisPayload parsePost(@RequestBody @Valid QrisPayload payload){
        return payload;
    }

    @GetMapping("config")
    public Map<Object, Object> config() {
        return Map.of(
                "configurers", toStrings(configurers),
                "converters", toStrings(converters),
                "requestMappingHandlerAdapter-converters",
                toStrings(requestMappingHandlerAdapter.getMessageConverters()));
    }

    private Object toStrings(Collection<?> collection) {
        return collection != null
                ? collection.stream().map(Object::toString).collect(Collectors.toList())
                : "N/A";
    }
}
