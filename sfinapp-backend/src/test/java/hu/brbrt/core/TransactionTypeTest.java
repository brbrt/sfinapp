package hu.brbrt.core;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;


public class TransactionTypeTest {

    @Test
    public void shouldCalculateTransferWhenToAccountIsPresent() {
        TransactionType result = TransactionType.calculate("acc", 800);
        assertThat(result).isEqualTo(TransactionType.Transfer);
    }

    @Test
    public void shouldCalculateIncomeWhenAmountIsPositive() {
        TransactionType result = TransactionType.calculate(null, 800);
        assertThat(result).isEqualTo(TransactionType.Income);
    }

    @Test
    public void shouldCalculateExpenseWhenAmountIsNegative() {
        TransactionType result = TransactionType.calculate(null, -800);
        assertThat(result).isEqualTo(TransactionType.Expense);
    }

}