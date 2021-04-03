package id.purnama.qris.validation;

import id.purnama.qris.QrisDataObject;
import id.purnama.qris.validation.constraints.PostalCode;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <b>4.7.15</b> Postal Code (ID "61")<br/>
 * Postal Code untuk mengindikasikan kode pos tempat merchant beroperasi. Wajib ditampilkan jika Value ID “58” adalah “ID” (Indonesia).
 */
public class PostalCodeValidator implements ConstraintValidator<PostalCode, Map<Integer, QrisDataObject>> {

    @Override
    public boolean isValid(Map<Integer, QrisDataObject> value, ConstraintValidatorContext context) {
        if("ID".equals(value.get(58).getValue())){
            if(value.get(61) != null){
                return isNumber(value.get(61).getValue());
            }
            return false;
        }
        return true;
    }

    private boolean isNumber(String str){
        String regex = "^[0-9]{5}$";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(str);
        return m.matches();
    }
}