package id.purnama.qris.validation.constraints;

import id.purnama.qris.validation.IdNotNullValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 *
 */
@Documented
@Constraint(validatedBy = {IdNotNullValidator.class})
@Target({FIELD})
@Retention(RUNTIME)
@Repeatable(IdNotNull.List.class)
public @interface IdNotNull {

    String message() default "ID: {id} tidak boleh kosong.";

    Class<?>[] groups() default {};

    int id() default 0;

    Class<? extends Payload>[] payload() default {};

    /**
     * Defines several {@link IdNotNull} annotations on the same element.
     *
     * @see IdNotNull
     */
    @Target({ FIELD })
    @Retention(RUNTIME)
    @Documented
    @interface List {

        IdNotNull[] value();
    }
}
