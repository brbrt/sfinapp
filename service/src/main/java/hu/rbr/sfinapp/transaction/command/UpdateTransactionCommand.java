package hu.rbr.sfinapp.transaction.command;

import hu.rbr.sfinapp.core.command.Command;

import javax.validation.constraints.NotNull;

public class UpdateTransactionCommand extends Command {

    public int id;

    @NotNull(message = "Transaction is required!")
    public TransactionItem transaction;

    public UpdateTransactionCommand(Integer id, TransactionItem transaction) {
        this.id = id;
        this.transaction = transaction;
    }

}
