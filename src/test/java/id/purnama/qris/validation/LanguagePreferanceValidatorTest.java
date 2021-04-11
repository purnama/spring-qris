package id.purnama.qris.validation;

import id.purnama.qris.object.QrisDataObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.stubbing.OngoingStubbing;

import javax.validation.ConstraintValidatorContext;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class LanguagePreferanceValidatorTest {
    private ConstraintValidatorContext constraintValidatorContext = mock(ConstraintValidatorContext.class);

    private LanguagePreferanceValidator languagePreferanceValidator;

    @BeforeEach
    private void before(){
        this.languagePreferanceValidator = LanguagePreferanceValidator.builder().build();
    }

    @Test
    void isValidTestNoTag(){
        QrisDataObject qrisDataObject = new QrisDataObject("10", "10", "10");
        assertTrue(this.languagePreferanceValidator.isValid(qrisDataObject, constraintValidatorContext));
    }

    @Test
    @SuppressWarnings("unchecked")
    void isValidTestValueNotFound(){
        QrisDataObject qrisDataObject = new QrisDataObject("64", "10", "10");
        var map = mock(Map.class);
        qrisDataObject.setTemplateMap(map);
        when(map.get(any())).thenReturn(new QrisDataObject("00", "", "123"));
        assertFalse(this.languagePreferanceValidator.isValid(qrisDataObject, constraintValidatorContext));
    }
}
