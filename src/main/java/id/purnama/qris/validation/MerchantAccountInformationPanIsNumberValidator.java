package id.purnama.qris.validation;

import id.purnama.qris.object.QrisDataObject;
import id.purnama.qris.validation.constraints.MerchantAccountInformationPanIsNumber;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class MerchantAccountInformationPanIsNumberValidator implements ConstraintValidator<MerchantAccountInformationPanIsNumber, QrisDataObject> {

    @Override
    public boolean isValid(QrisDataObject value, ConstraintValidatorContext context) {
        if(value.getIntId() > 25 && value.getIntId() < 46){
            try {
                Double.parseDouble(value.getTemplateMap().get(1).getValue());
            } catch (NumberFormatException nfe) {
                return false;
            }
        }
        return true;
    }
}