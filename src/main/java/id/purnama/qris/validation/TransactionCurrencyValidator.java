package id.purnama.qris.validation;

import id.purnama.qris.QrisDataObject;
import id.purnama.qris.validation.constraints.TransactionCurrency;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Currency;
import java.util.Map;
import java.util.Set;

/**
 * <b>4.7.7</b> Transaction Currency (ID "53")<br />
 * <b>4.7.7.1</b> Transaction Currency harus mengacu pada [ISO 4217] dan merupakan 3 digit angka yang merepresentasikan mata uang . Indonesia Rupiah direpresentasikan oleh Value "360". Value tersebut wajib digunakan jika Value ID “58” adalah “ID” (Indonesia) <br />
 */
public class TransactionCurrencyValidator implements ConstraintValidator<TransactionCurrency, Map<Integer, QrisDataObject>> {

    @Override
    public boolean isValid(Map<Integer, QrisDataObject> value, ConstraintValidatorContext context) {

        Set<Currency> availableCurrencies = Currency.getAvailableCurrencies();
        for (Currency currency : availableCurrencies) {
            if (currency.getNumericCodeAsString().equals(value.get(53).getValue())) {
                if ("ID".equals(value.get(58).getValue())) {
                    return ("360".equals(value.get(53).getValue())) ? true : false;
                }
            }
        }
        return false;

    }
}