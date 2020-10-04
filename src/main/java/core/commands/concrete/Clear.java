package core.commands.concrete;

import core.commands.Command;
import core.commands.Receiver;

import java.io.IOException;

public class Clear extends Command {
    private static final long serialVersionUID = 32L;
    transient private Receiver receiver;

    public Clear(Receiver receiver) {
        this.receiver = receiver;
    }

    public Clear() {

    }

    @Override
    protected void writeInfo() {
        System.out.println("Команда < clear > : [очищает коллекцию];");
    }

    @Override
    protected void execute(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        if (args.length > 1) {
            System.out.println("Введен не нужный аргумент. Команда приведена к базовой команде < clear >.");
        }
        receiver.clear();
    }
}
