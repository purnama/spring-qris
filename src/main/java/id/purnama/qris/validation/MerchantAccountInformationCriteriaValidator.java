package id.purnama.qris.validation;

import id.purnama.qris.QrisDataObject;
import id.purnama.qris.QrisMerchantCriteria;
import id.purnama.qris.validation.constraints.MerchantAccountInformationCriteria;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class MerchantAccountInformationCriteriaValidator implements ConstraintValidator<MerchantAccountInformationCriteria, QrisDataObject> {

    @Override
    public boolean isValid(QrisDataObject value, ConstraintValidatorContext context) {
        if(value.getIntId() > 25 && value.getIntId() < 52){
            for (QrisMerchantCriteria enums : QrisMerchantCriteria.values()) {
                if (enums.name().equals(value.getTemplateMap().get(2).getValue())) {
                    return true;
                }
            }
            return false;
        }
        return true;
    }
}
