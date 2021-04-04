package id.purnama.qris;

import id.purnama.qris.object.QrisPayload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import javax.validation.Validator;

@Component
public class QrisStringToPayloadConverter implements Converter<String, QrisPayload> {

    @Autowired
    private QrisParser qrisParser;

    @Autowired
    Validator validator;

    @Override
    public QrisPayload convert(String s) {
        QrisPayload payload = qrisParser.parse(s);
        validator.validate(payload);
        return payload;
    }
}