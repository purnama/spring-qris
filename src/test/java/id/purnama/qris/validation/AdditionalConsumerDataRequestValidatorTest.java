package id.purnama.qris.validation;

import id.purnama.qris.object.QrisDataObject;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.validation.ConstraintValidatorContext;

import static org.junit.jupiter.api.Assertions.assertTrue;

class AdditionalConsumerDataRequestValidatorTest {

    @Test
    void isValidTest(){
        assertTrue(AdditionalConsumerDataRequestValidator.builder().build().isValid(Mockito.mock(QrisDataObject.class), Mockito.mock(ConstraintValidatorContext.class)));
    }
}
