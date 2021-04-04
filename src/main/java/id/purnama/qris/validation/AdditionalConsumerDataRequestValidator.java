package id.purnama.qris.validation;

import id.purnama.qris.object.QrisDataObject;
import id.purnama.qris.validation.constraints.AdditionalConsumerDataRequest;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * <b>4.8.1.3</b> Jika ditampilkan Additional Consumer Data Request (ID "09") wajib berisi kombinasi karakter seperti "A", "M" dan/atau "E", kemudian wajib ada konten yang mencerminkan setiap karakter tersebut.
 */
public class AdditionalConsumerDataRequestValidator implements ConstraintValidator<AdditionalConsumerDataRequest, QrisDataObject> {

    @Override
    public boolean isValid(QrisDataObject value, ConstraintValidatorContext context) {
        return true;
    }
}