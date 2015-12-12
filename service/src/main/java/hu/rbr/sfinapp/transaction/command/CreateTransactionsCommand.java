package hu.rbr.sfinapp.transaction.command;

import hu.rbr.sfinapp.core.command.Command;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

public class CreateTransactionsCommand extends Command {

    @NotNull(message = "You must specify at least one transaction!")
    @Size(min = 1, message = "You must specify at least one transaction!")
    public List<TransactionItem> transactions;

    public CreateTransactionsCommand(List<TransactionItem> transactions) {
        this.transactions = transactions;
    }

}
