package id.purnama.qris.validation;

import id.purnama.qris.object.QrisDataObject;
import id.purnama.qris.validation.constraints.LanguagePreferance;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Locale;

/**
 * <b>4.9.2</b> Language Preference (ID "00")
 * <b>4.9.2.1</b> Language Preference wajib berisi dua karakter alfabet yang didefinisikan oleh [ISO 639]. Value pada data object Language Preference (ID "00") harus sesuai dengan Merchant Name—Alternate Language dan Merchant City—Alternate Language.
 */
public class LanguagePreferanceValidator implements ConstraintValidator<LanguagePreferance, QrisDataObject> {

    @Override
    public boolean isValid(QrisDataObject value, ConstraintValidatorContext context) {
        if(value.getIntId().equals(64)){
            String localeStr = value.getTemplateMap().get(0).getValue();
            for(Locale locale : Locale.getAvailableLocales()){
                if(localeStr.equals(locale.getISO3Language())){
                    return true;
                }
            }
            return false;
        }
        return true;
    }
}