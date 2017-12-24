package hu.brbrt.core;

public enum TransactionType {
    Income,
    Expense,
    Transfer;

    public static TransactionType calculate(Object toAccount, double amount) {
        if (toAccount != null) {
            return Transfer;
        }

        if (amount > 0) {
            return Income;
        }

        return Expense;
    }

}
