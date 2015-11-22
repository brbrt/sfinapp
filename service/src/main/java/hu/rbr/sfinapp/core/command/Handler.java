package hu.rbr.sfinapp.core.command;

public interface Handler<T extends Command> {

    void handle(T command);

    Class<T> commandClass();

}
