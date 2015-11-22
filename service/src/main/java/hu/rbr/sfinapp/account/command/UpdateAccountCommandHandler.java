package hu.rbr.sfinapp.account.command;


import hu.rbr.sfinapp.account.AccountService;
import hu.rbr.sfinapp.core.command.Handler;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class UpdateAccountCommandHandler implements Handler<UpdateAccountCommand> {

    private final AccountService accountService;

    @Inject
    public UpdateAccountCommandHandler(AccountService accountService) {
        this.accountService = accountService;
    }

    @Override
    public void handle(UpdateAccountCommand command) {
        accountService.update(command);
    }

    @Override
    public Class<UpdateAccountCommand> commandClass() {
        return UpdateAccountCommand.class;
    }

}
