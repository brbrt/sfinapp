package hu.rbr.sfinapp.account.command;

import hu.rbr.sfinapp.core.command.Command;
import org.hibernate.validator.constraints.NotBlank;

public class CreateAccountCommand extends Command {

    @NotBlank(message = "Name is required!")
    public String name;

    public String description;

    public boolean technical;

}
