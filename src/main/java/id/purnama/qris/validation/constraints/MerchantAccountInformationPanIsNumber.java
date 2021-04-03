package id.purnama.qris.validation.constraints;

import id.purnama.qris.validation.MerchantAccountInformationPanIsNumberValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Constraint(validatedBy = {MerchantAccountInformationPanIsNumberValidator.class})
@Target({TYPE})
@Retention(RUNTIME)
public @interface MerchantAccountInformationPanIsNumber {

    String message() default "Merchant Account Information field {id} must min:{min} and max:{max} characters long";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    /**
     * @return id
     */
    int id() default 0;

    /**
     * @return size the element must be higher or equal to
     */
    int min() default 0;

    /**
     * @return size the element must be lower or equal to
     */
    int max() default Integer.MAX_VALUE;
}
