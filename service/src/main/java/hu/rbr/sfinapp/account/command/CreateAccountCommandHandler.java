package hu.rbr.sfinapp.account.command;


import hu.rbr.sfinapp.account.AccountCommandService;
import hu.rbr.sfinapp.core.command.Handler;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class CreateAccountCommandHandler implements Handler<CreateAccountCommand> {

    private final AccountCommandService accountCommandService;

    @Inject
    public CreateAccountCommandHandler(AccountCommandService accountCommandService) {
        this.accountCommandService = accountCommandService;
    }

    @Override
    public void handle(CreateAccountCommand command) {
        accountCommandService.create(command);
    }

    @Override
    public Class<CreateAccountCommand> commandClass() {
        return CreateAccountCommand.class;
    }

}
