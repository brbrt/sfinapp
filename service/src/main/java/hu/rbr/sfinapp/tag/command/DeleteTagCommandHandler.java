package hu.rbr.sfinapp.tag.command;


import hu.rbr.sfinapp.account.AccountCommandService;
import hu.rbr.sfinapp.core.command.Handler;
import hu.rbr.sfinapp.tag.TagCommandService;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class DeleteTagCommandHandler implements Handler<DeleteTagCommand> {

    private final TagCommandService tagCommandService;

    @Inject
    public DeleteTagCommandHandler(TagCommandService tagCommandService) {
        this.tagCommandService = tagCommandService;
    }

    @Override
    public void handle(DeleteTagCommand command) {
        tagCommandService.delete(command);
    }

    @Override
    public Class<DeleteTagCommand> commandClass() {
        return DeleteTagCommand.class;
    }

}
