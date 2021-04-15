package id.purnama.qris.validation;

import id.purnama.qris.object.QrisDataObject;
import id.purnama.qris.validation.constraints.CharLength;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CharLengthValidator implements ConstraintValidator<CharLength, QrisDataObject> {

    private int id;
    private int min;
    private int max;

    @Override
    public void initialize(CharLength constraintAnnotation) {
        this.id = constraintAnnotation.id();
        this.min = constraintAnnotation.min();
        this.max = constraintAnnotation.max();
    }

    @Override
    public boolean isValid(QrisDataObject value, ConstraintValidatorContext context) {
        if (value.getIntId() == this.id) {
            return value.getIntLength() >= this.min &&
                    value.getIntLength() <= this.max;
        }
        return true;
    }
}