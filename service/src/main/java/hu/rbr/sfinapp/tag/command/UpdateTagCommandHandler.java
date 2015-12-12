package hu.rbr.sfinapp.tag.command;


import hu.rbr.sfinapp.account.AccountCommandService;
import hu.rbr.sfinapp.core.command.Handler;
import hu.rbr.sfinapp.tag.TagCommandService;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class UpdateTagCommandHandler implements Handler<UpdateTagCommand> {

    private final TagCommandService tagCommandService;

    @Inject
    public UpdateTagCommandHandler(TagCommandService tagCommandService) {
        this.tagCommandService = tagCommandService;
    }

    @Override
    public void handle(UpdateTagCommand command) {
        tagCommandService.update(command);
    }

    @Override
    public Class<UpdateTagCommand> commandClass() {
        return UpdateTagCommand.class;
    }

}
