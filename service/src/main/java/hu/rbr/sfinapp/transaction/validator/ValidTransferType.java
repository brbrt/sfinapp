package hu.rbr.sfinapp.transaction.validator;

import hu.rbr.sfinapp.transaction.Transaction;

import javax.validation.Constraint;
import java.lang.annotation.*;

@Target({ ElementType.TYPE, ElementType.ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = { ValidTransferTypeValidator.class })
@Documented
@SuppressWarnings("UnusedDeclaration")
public @interface ValidTransferType {

    String message() default "If transaction type is Transfer, toAccount is required!";

    Class<?>[] groups() default { };

    Class<? extends Transaction>[] payload() default { };

}