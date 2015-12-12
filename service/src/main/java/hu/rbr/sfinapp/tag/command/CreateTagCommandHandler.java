package hu.rbr.sfinapp.tag.command;


import hu.rbr.sfinapp.account.AccountCommandService;
import hu.rbr.sfinapp.core.command.Handler;
import hu.rbr.sfinapp.tag.TagCommandService;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class CreateTagCommandHandler implements Handler<CreateTagCommand> {

    private final TagCommandService tagCommandService;

    @Inject
    public CreateTagCommandHandler(TagCommandService tagCommandService) {
        this.tagCommandService = tagCommandService;
    }

    @Override
    public void handle(CreateTagCommand command) {
        tagCommandService.create(command);
    }

    @Override
    public Class<CreateTagCommand> commandClass() {
        return CreateTagCommand.class;
    }

}
