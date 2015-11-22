package hu.rbr.sfinapp.account.command;


import hu.rbr.sfinapp.account.AccountService;
import hu.rbr.sfinapp.core.command.Handler;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class DeleteAccountCommandHandler implements Handler<DeleteAccountCommand> {

    private final AccountService accountService;

    @Inject
    public DeleteAccountCommandHandler(AccountService accountService) {
        this.accountService = accountService;
    }

    @Override
    public void handle(DeleteAccountCommand command) {
        accountService.delete(command);
    }

    @Override
    public Class<DeleteAccountCommand> commandClass() {
        return DeleteAccountCommand.class;
    }

}
