package hu.rbr.sfinapp.account.command;


import hu.rbr.sfinapp.account.AccountCommandService;
import hu.rbr.sfinapp.core.command.Handler;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class DeleteAccountCommandHandler implements Handler<DeleteAccountCommand> {

    private final AccountCommandService accountCommandService;

    @Inject
    public DeleteAccountCommandHandler(AccountCommandService accountCommandService) {
        this.accountCommandService = accountCommandService;
    }

    @Override
    public void handle(DeleteAccountCommand command) {
        accountCommandService.delete(command);
    }

    @Override
    public Class<DeleteAccountCommand> commandClass() {
        return DeleteAccountCommand.class;
    }

}
