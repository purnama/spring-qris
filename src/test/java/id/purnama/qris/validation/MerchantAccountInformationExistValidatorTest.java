package id.purnama.qris.validation;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.validation.ConstraintValidatorContext;
import java.util.LinkedHashMap;
import java.util.Map;

class MerchantAccountInformationExistValidatorTest {

    @Test
    void isValid(){
        Map<Integer, id.purnama.qris.object.QrisDataObject> map = new LinkedHashMap<>();
        MerchantAccountInformationExistValidator merchantAccountInformationExistValidator = new MerchantAccountInformationExistValidator();
        Assertions.assertFalse(merchantAccountInformationExistValidator.isValid(map, Mockito.mock(ConstraintValidatorContext.class)));
    }
}
