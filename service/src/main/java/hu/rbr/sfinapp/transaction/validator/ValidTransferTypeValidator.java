package hu.rbr.sfinapp.transaction.validator;

import hu.rbr.sfinapp.transaction.Transaction;
import hu.rbr.sfinapp.transaction.TransactionType;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ValidTransferTypeValidator implements ConstraintValidator<ValidTransferType, Transaction> {

    @Override
    public void initialize(ValidTransferType constraintAnnotation) {}

    @Override
    public boolean isValid(Transaction transaction, ConstraintValidatorContext context) {
        if (transaction.type == TransactionType.Transfer && transaction.toAccountId == null) {
            return false;
        }

        return true;
    }

}
