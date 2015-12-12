package hu.rbr.sfinapp.transaction;

import hu.rbr.sfinapp.transaction.command.TransactionItem;

import static hu.rbr.sfinapp.transaction.TransactionType.Expense;
import static hu.rbr.sfinapp.transaction.TransactionType.Income;
import static hu.rbr.sfinapp.transaction.TransactionType.Transfer;

public class TransactionItemConverter {

    public static Transaction convertToTransaction(TransactionItem item) {
        Transaction tr = new Transaction();

        tr.date = item.date;
        tr.amount = item.amount;
        tr.description = item.description;
        tr.type = item.type;
        tr.accountId = item.accountId;
        tr.toAccountId = item.toAccountId;
        tr.comment = item.comment;
        tr.tagIds = item.tagIds;

        correctAmountBasedOnType(tr);

        return tr;
    }

    private static void correctAmountBasedOnType(Transaction tr) {
        if ((tr.type == Expense && tr.amount > 0) ||
            (tr.type == Income && tr.amount < 0) ||
            (tr.type == Transfer && tr.amount < 0)) {

            tr.amount *= -1;
        }
    }

}
