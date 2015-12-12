package hu.rbr.sfinapp.transaction.command;


import hu.rbr.sfinapp.core.command.Handler;
import hu.rbr.sfinapp.transaction.TransactionCommandService;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class CreateTransactionsCommandHandler implements Handler<CreateTransactionsCommand> {

    private final TransactionCommandService transactionCommandService;

    @Inject
    public CreateTransactionsCommandHandler(TransactionCommandService transactionCommandService) {
        this.transactionCommandService = transactionCommandService;
    }


    @Override
    public void handle(CreateTransactionsCommand command) {
        transactionCommandService.create(command);
    }

    @Override
    public Class<CreateTransactionsCommand> commandClass() {
        return CreateTransactionsCommand.class;
    }

}
