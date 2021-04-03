package id.purnama.qris.validation.constraints;

import id.purnama.qris.validation.MerchantCategoryCodeValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * <b>4.7.6</b> Merchant Category Code (ID "52")
 * <b>4.7.6.1</b> Merchant Category Code (MCC) harus memuat informasi MCC yang didefinisikan oleh [ISO 18245].
 */
@Documented
@Constraint(validatedBy = {MerchantCategoryCodeValidator.class})
@Target({FIELD})
@Retention(RUNTIME)
public @interface MerchantCategoryCode {

    String message() default "Merchant Category Code (MCC) harus memuat informasi MCC yang didefinisikan oleh [ISO 18245].";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
