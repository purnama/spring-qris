package id.purnama.qris;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class QrisStringConverter implements Converter<String, QrisPayload> {

    @Autowired
    private QrisParser qrisParser;

    @Override
    public QrisPayload convert(String s) {
        QrisPayload payload = qrisParser.parse(s);
        return payload;
    }
}