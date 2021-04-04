package id.purnama.qris.validation;

import id.purnama.qris.object.QrisDataObject;
import id.purnama.qris.validation.constraints.AdditionalDataFieldAsterisk;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * <b>4.8.1.2</b> Jika ditampilkan, konten dari data object ID "01" - "08" wajib berupa "***" (tiga karakter asterisk) atau Value yang telah didefinisi oleh merchant. Ketersediaan dari "***" (tiga karakter asterisk) mengindikasikan bahwa aplikasi mobile meminta untuk memasukan informasi yang diperlukan.<br />
 */
public class AdditionalDataFieldAsteriskValidator implements ConstraintValidator<AdditionalDataFieldAsterisk, QrisDataObject> {

    @Override
    public boolean isValid(QrisDataObject value, ConstraintValidatorContext context) {
        if(value.getIntId().equals(62)){
            for(int i=1; i<=8; i++){
                if(value.getTemplateMap().containsKey(i)){
                    String str = value.getTemplateMap().get(i).getValue();
                    if(!"".equals(str)){
                        if(str.length() == 3){
                            return "***".equals(str);
                        }
                        return true;
                    }
                    return false;
                }
            }
            return true;
        }
        return true;
    }
}