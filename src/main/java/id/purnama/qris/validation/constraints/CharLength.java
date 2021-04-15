package id.purnama.qris.validation.constraints;

import id.purnama.qris.validation.CharLengthValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Constraint(validatedBy = {CharLengthValidator.class})
@Target({TYPE})
@Retention(RUNTIME)
@Repeatable(CharLength.List.class)
public @interface CharLength {

    /**
     *
     * @return String
     */
    String message() default "Field \"{id}\" have min:{min} and max{max} characters long.";

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

    /**
     *
     * @return int
     */
    int id() default 0;
    /**
     *
     * @return int
     */
    int min() default 0;
    /**
     *
     * @return int
     */
    int max() default Integer.MAX_VALUE;

    /**
     * Defines several {@link CharLength} annotations on the same element.
     *
     * @see CharLength
     */
    @Target({ TYPE })
    @Retention(RUNTIME)
    @Documented
    @interface List {
        /**
         *
         * @return MerchantAccountInformationCharLength
         */
        CharLength[] value();
    }
}