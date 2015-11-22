package hu.rbr.sfinapp.account.command;


import hu.rbr.sfinapp.account.AccountService;
import hu.rbr.sfinapp.core.command.Handler;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class CreateAccountCommandHandler implements Handler<CreateAccountCommand> {

    private final AccountService accountService;

    @Inject
    public CreateAccountCommandHandler(AccountService accountService) {
        this.accountService = accountService;
    }

    @Override
    public void handle(CreateAccountCommand command) {
        accountService.create(command);
    }

    @Override
    public Class<CreateAccountCommand> commandClass() {
        return CreateAccountCommand.class;
    }

}
