package id.purnama.qris.validation.constraints;

import id.purnama.qris.validation.MerchantAccountInformationMandatoryFieldValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Constraint(validatedBy = {MerchantAccountInformationMandatoryFieldValidator.class})
@Target({TYPE})
@Retention(RUNTIME)
@Repeatable(MerchantAccountInformationMandatoryField.List.class)
public @interface MerchantAccountInformationMandatoryField {

    String message() default "Merchant Account Information field \"{id}\" is mandatory";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    int from() default 0;
    int to() default 0;
    int id() default 0;

    /**
     * Defines several {@link MerchantAccountInformationMandatoryField} annotations on the same element.
     *
     * @see MerchantAccountInformationMandatoryField
     */
    @Target({ TYPE })
    @Retention(RUNTIME)
    @Documented
    @interface List {

        MerchantAccountInformationMandatoryField[] value();
    }
}
