package id.purnama.qris.validation;

import id.purnama.qris.QrisDataObject;
import id.purnama.qris.validation.constraints.TipValuePercentage;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Map;

/**
 * <b>4.7.11.1</b> Tip Value Percentage wajib ditampilkan hanya jika data object Tip Indicator (ID "55") menampilkan Value "03" dengan Value yang digunakan berkisar antara “00.01” dan “99.99” untuk merepresentasikan persentase 0.01% hingga 99.99%.
 */
public class TipValuePercentageValidator implements ConstraintValidator<TipValuePercentage, Map<Integer, QrisDataObject>> {

    @Override
    public boolean isValid(Map<Integer, QrisDataObject> value, ConstraintValidatorContext context) {
        if (value.get(57) != null) {
            Double amount = Double.valueOf(value.get(57).getValue());
            return amount > 0 && amount < 100;
        }
        return true;
    }
}