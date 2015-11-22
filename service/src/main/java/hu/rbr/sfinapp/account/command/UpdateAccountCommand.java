package hu.rbr.sfinapp.account.command;

import hu.rbr.sfinapp.core.command.Command;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

public class UpdateAccountCommand extends Command {

    @NotNull(message = "Id is required!")
    public Integer id;

    @NotBlank(message = "Name is required!")
    public String name;

    public String description;

    public boolean technical;

}
