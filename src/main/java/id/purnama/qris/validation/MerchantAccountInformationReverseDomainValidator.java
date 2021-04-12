package id.purnama.qris.validation;

import id.purnama.qris.object.QrisDataObject;
import id.purnama.qris.validation.constraints.MerchantAccountInformationReverseDomain;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <b>4.7.5.3</b> Reverse Domain pada ID “26”-“45” dengan sub ID “00” harus memiliki nilai default “00” atau dapat berisi informasi reverse domain-nya.
 */
public class MerchantAccountInformationReverseDomainValidator implements ConstraintValidator<MerchantAccountInformationReverseDomain, QrisDataObject> {

    @Override
    public boolean isValid(QrisDataObject value, ConstraintValidatorContext context) {
        if (value.getIntId() > 25 && value.getIntId() < 52 && !"00".equals(value.getTemplateMap().get(0).getValue())) {
            return isValidDomain(reverseDomainNameString(value.getTemplateMap().get(0).getValue()));
        }
        return true;
    }

    private String reverseDomainNameString(String s) {
        List<String> components = Arrays.asList(s.split("\\."));
        Collections.reverse(components);
        return String.join(".", components.toArray(new String[0]));
    }

    private boolean isValidDomain(String str) {
        // Regex to check valid domain name.
        String regex = "^((?!-)[A-Za-z0–9-]{1,63}(?<!-)\\.)+[A-Za-z]{2,6}$";

        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(str);
        return m.matches();
    }
}