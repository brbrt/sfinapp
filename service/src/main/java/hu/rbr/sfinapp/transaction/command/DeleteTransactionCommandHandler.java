package hu.rbr.sfinapp.transaction.command;


import hu.rbr.sfinapp.core.command.Handler;
import hu.rbr.sfinapp.transaction.TransactionCommandService;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class DeleteTransactionCommandHandler implements Handler<DeleteTransactionCommand> {

    private final TransactionCommandService transactionCommandService;

    @Inject
    public DeleteTransactionCommandHandler(TransactionCommandService transactionCommandService) {
        this.transactionCommandService = transactionCommandService;
    }

    @Override
    public void handle(DeleteTransactionCommand command) {
        transactionCommandService.delete(command);
    }

    @Override
    public Class<DeleteTransactionCommand> commandClass() {
        return DeleteTransactionCommand.class;
    }

}
