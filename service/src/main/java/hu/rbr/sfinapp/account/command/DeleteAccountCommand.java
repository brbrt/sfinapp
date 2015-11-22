package hu.rbr.sfinapp.account.command;

import hu.rbr.sfinapp.core.command.Command;

import javax.validation.constraints.NotNull;

public class DeleteAccountCommand extends Command {

    @NotNull(message = "Id is required!")
    public Integer id;

    public DeleteAccountCommand(Integer id) {
        this.id = id;
    }

}
