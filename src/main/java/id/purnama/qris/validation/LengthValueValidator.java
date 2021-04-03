package id.purnama.qris.validation;

import id.purnama.qris.QrisDataObject;
import id.purnama.qris.validation.constraints.LengthValue;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * <b>4.4</b> Panjang Karakter Data object <br/>
 * <b>4.4.1.1</b> Panjang karakter wajib sama dengan jumlah karakter dalam Value field.
 */
public class LengthValueValidator implements ConstraintValidator<LengthValue, QrisDataObject> {

    @Override
    public boolean isValid(QrisDataObject value, ConstraintValidatorContext context) {
        return value.getValue().length() == value.getIntLength();
    }
}
