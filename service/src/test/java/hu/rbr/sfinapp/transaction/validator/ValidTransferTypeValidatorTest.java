package hu.rbr.sfinapp.transaction.validator;

import hu.rbr.sfinapp.transaction.Transaction;
import hu.rbr.sfinapp.transaction.TransactionType;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

public class ValidTransferTypeValidatorTest {

    private ValidTransferTypeValidator validator;

    @Before
    public void init() {
        validator = new ValidTransferTypeValidator();
    }

    @Test
    public void valid() {
        Transaction tr = new Transaction();
        tr.type = TransactionType.Transfer;
        tr.toAccountId = 5;

        boolean valid = validator.isValid(tr, null);
        assertThat(valid, equalTo(true));
    }

    @Test
    public void typeIsNull() {
        Transaction tr = new Transaction();
        tr.type = null;

        boolean valid = validator.isValid(tr, null);
        assertThat(valid, equalTo(true));
    }

    @Test
    public void missingToAccountIdOnTransfer() {
        Transaction tr = new Transaction();
        tr.type = TransactionType.Transfer;
        tr.toAccountId = null;

        boolean valid = validator.isValid(tr, null);
        assertThat(valid, equalTo(false));
    }

    @Test
    public void missingToAccountIdOnIncome() {
        Transaction tr = new Transaction();
        tr.type = TransactionType.Income;
        tr.toAccountId = null;

        boolean valid = validator.isValid(tr, null);
        assertThat(valid, equalTo(true));
    }

    @Test
    public void missingToAccountIdOnExpense() {
        Transaction tr = new Transaction();
        tr.type = TransactionType.Expense;
        tr.toAccountId = null;

        boolean valid = validator.isValid(tr, null);
        assertThat(valid, equalTo(true));
    }
}
