package id.purnama.qris.controller;

import id.purnama.qris.object.QrisPayload;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import javax.validation.Validator;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
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

    @GetMapping(value = "/parse", produces = {"application/qris", MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public QrisPayload parseGet(@RequestParam QrisPayload payload){
        Set<ConstraintViolation<QrisPayload>> violationSet = validator.validate(payload);
        if (!violationSet.isEmpty() ) throw new ConstraintViolationException(violationSet);
        return payload;
    }

    @PostMapping(value = "/parse", consumes = "application/qris", produces = "application/qris")
    public QrisPayload parsePost(QrisPayload payload){
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

    @GetMapping("/test")
    public TestData test(@Valid TestData testData){
        return testData;
    }
}
