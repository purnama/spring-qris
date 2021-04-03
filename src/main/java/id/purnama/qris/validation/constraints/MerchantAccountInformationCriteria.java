package id.purnama.qris.validation.constraints;

import id.purnama.qris.validation.MerchantAccountInformationCriteriaValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * <b>4.7.5.3</b> Reverse Domain pada ID “26”-“45” dengan sub ID “00” harus memiliki nilai default “00” atau dapat berisi informasi reverse domain-nya.
 */
@Documented
@Constraint(validatedBy = {MerchantAccountInformationCriteriaValidator.class})
@Target({TYPE})
@Retention(RUNTIME)
public @interface MerchantAccountInformationCriteria {

    String message() default "Merchant Account Information Personal Account Number field mandatory.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
