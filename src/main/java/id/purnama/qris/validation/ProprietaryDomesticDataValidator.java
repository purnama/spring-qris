package id.purnama.qris.validation;

import id.purnama.qris.QrisDataObject;
import id.purnama.qris.validation.constraints.AdditionalDataFieldAsterisk;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * <b>4.8.1.4</b> Jika ID “99” diisi, maka template mengacu pada Tabel 4.8 dengan panjang karakter yang akan tidak lebih dari 91 karakter.
 */
public class ProprietaryDomesticDataValidator implements ConstraintValidator<AdditionalDataFieldAsterisk, QrisDataObject> {

    @Override
    public boolean isValid(QrisDataObject value, ConstraintValidatorContext context) {
        if(value.getIntId().equals(62)){
            if(value.getTemplateMap().containsKey(99)){
                return value.getTemplateMap().get(99).getValue().length() <= 91;
            }
        }
        return true;
    }
}