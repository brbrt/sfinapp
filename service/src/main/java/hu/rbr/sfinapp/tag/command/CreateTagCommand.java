package hu.rbr.sfinapp.tag.command;

import hu.rbr.sfinapp.core.command.Command;
import org.hibernate.validator.constraints.NotBlank;

public class CreateTagCommand extends Command {

    @NotBlank(message = "Name is required!")
    public String name;

    public String description;

}
