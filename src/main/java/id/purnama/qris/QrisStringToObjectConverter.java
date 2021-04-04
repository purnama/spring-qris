package id.purnama.qris;

import id.purnama.qris.object.Qris;
import id.purnama.qris.object.QrisPayload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import javax.validation.Validator;

@Component
public class QrisStringToObjectConverter implements Converter<String, Qris> {

    @Autowired
    private QrisParser qrisParser;

    @Autowired
    private Validator validator;

    @Override
    public Qris convert(String s) {
        QrisPayload payload = qrisParser.parse(s);
        validator.validate(payload);
        return qrisParser.map(payload.getQrisRoot());
    }
}