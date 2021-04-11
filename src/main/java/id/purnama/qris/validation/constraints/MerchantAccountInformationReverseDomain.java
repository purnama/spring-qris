package id.purnama.qris.validation.constraints;

import id.purnama.qris.validation.MerchantAccountInformationReverseDomainValidator;

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
@Constraint(validatedBy = {MerchantAccountInformationReverseDomainValidator.class})
@Target({TYPE})
@Retention(RUNTIME)
public @interface MerchantAccountInformationReverseDomain {

        /**
     *
     * @return String
     */
    String message() default "Merchant Account Information Personal Account Number field mandatory.";

    /**
     *
     * @return class
     */
    Class<?>[] groups() default {};

    /**
     *
     * @return class
     */
    Class<? extends Payload>[] payload() default {};
}
