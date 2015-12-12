package hu.rbr.sfinapp.transaction.command;

import hu.rbr.sfinapp.core.command.Command;

import javax.validation.constraints.NotNull;

public class DeleteTransactionCommand extends Command {

    @NotNull(message = "Id is required!")
    public Integer id;

    public DeleteTransactionCommand(Integer id) {
        this.id = id;
    }

}
