package id.purnama.qris.validation;

import id.purnama.qris.object.QrisDataObject;
import id.purnama.qris.object.MerchantCriteria;
import id.purnama.qris.validation.constraints.MerchantAccountInformationCriteria;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * <b>4.7.5.3</b> Reverse Domain pada ID “26”-“45” dengan sub ID “00” harus memiliki nilai default “00” atau dapat berisi informasi reverse domain-nya.
 */
public class MerchantAccountInformationCriteriaValidator implements ConstraintValidator<MerchantAccountInformationCriteria, QrisDataObject> {

    @Override
    public boolean isValid(QrisDataObject value, ConstraintValidatorContext context) {
        if(value.getIntId() > 25 && value.getIntId() < 52){
            for (MerchantCriteria enums : MerchantCriteria.values()) {
                if (enums.name().equals(value.getTemplateMap().get(3).getValue())) {
                    return true;
                }
            }
            return false;
        }
        return true;
    }
}
