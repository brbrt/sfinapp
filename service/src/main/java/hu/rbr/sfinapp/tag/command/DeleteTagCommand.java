package hu.rbr.sfinapp.tag.command;

import hu.rbr.sfinapp.core.command.Command;

import javax.validation.constraints.NotNull;

public class DeleteTagCommand extends Command {

    @NotNull(message = "Id is required!")
    public Integer id;

    public DeleteTagCommand(Integer id) {
        this.id = id;
    }

}
