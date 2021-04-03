package id.purnama.qris.validation.constraints;

import id.purnama.qris.validation.AdditionalConsumerDataRequestValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * <b>4.8.1.3</b> Jika ditampilkan Additional Consumer Data Request (ID "09") wajib berisi kombinasi karakter seperti "A", "M" dan/atau "E", kemudian wajib ada konten yang mencerminkan setiap karakter tersebut.
 */
@Documented
@Constraint(validatedBy = {AdditionalConsumerDataRequestValidator.class})
@Target({TYPE})
@Retention(RUNTIME)
public @interface AdditionalConsumerDataRequest {

    String message() default "Jika ditampilkan Additional Consumer Data Request (ID \"09\") wajib berisi kombinasi karakter seperti \"A\", \"M\" dan/atau \"E\"";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
