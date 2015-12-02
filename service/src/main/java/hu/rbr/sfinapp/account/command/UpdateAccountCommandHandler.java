package hu.rbr.sfinapp.account.command;


import hu.rbr.sfinapp.account.AccountCommandService;
import hu.rbr.sfinapp.core.command.Handler;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class UpdateAccountCommandHandler implements Handler<UpdateAccountCommand> {

    private final AccountCommandService accountCommandService;

    @Inject
    public UpdateAccountCommandHandler(AccountCommandService accountCommandService) {
        this.accountCommandService = accountCommandService;
    }

    @Override
    public void handle(UpdateAccountCommand command) {
        accountCommandService.update(command);
    }

    @Override
    public Class<UpdateAccountCommand> commandClass() {
        return UpdateAccountCommand.class;
    }

}
