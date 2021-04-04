package id.purnama.qris.validation;

import id.purnama.qris.object.QrisDataObject;
import id.purnama.qris.validation.constraints.MerchantNameAlternateLanguage;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class MerchantNameAlternateLanguageValidator implements ConstraintValidator<MerchantNameAlternateLanguage, QrisDataObject> {

    @Override
    public boolean isValid(QrisDataObject value, ConstraintValidatorContext context) {
        if(value.getIntId().equals(64)){
            return value.getTemplateMap().get(1) != null;
        }
        return true;
    }
}