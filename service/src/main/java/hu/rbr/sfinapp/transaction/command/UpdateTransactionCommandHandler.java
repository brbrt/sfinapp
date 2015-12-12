package hu.rbr.sfinapp.transaction.command;


import hu.rbr.sfinapp.core.command.Handler;
import hu.rbr.sfinapp.transaction.TransactionCommandService;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class UpdateTransactionCommandHandler implements Handler<UpdateTransactionCommand> {

    private final TransactionCommandService transactionCommandService;

    @Inject
    public UpdateTransactionCommandHandler(TransactionCommandService transactionCommandService) {
        this.transactionCommandService = transactionCommandService;
    }


    @Override
    public void handle(UpdateTransactionCommand command) {
        transactionCommandService.update(command);
    }

    @Override
    public Class<UpdateTransactionCommand> commandClass() {
        return UpdateTransactionCommand.class;
    }

}
