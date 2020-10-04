package core.commands.concrete;

import core.commands.Command;
import core.commands.Receiver;

import java.io.IOException;

public class Reorder extends Command {
    private static final long serialVersionUID = 32L;
    transient private Receiver receiver;

    public Reorder(Receiver receiver) {
        this.receiver = receiver;
    }

    public Reorder() {

    }

    @Override
    protected void writeInfo() {
        System.out.println("Команда < reorder > : [отсортировывает коллекцию в порядке, обратном нынешнему];");
    }

    @Override
    protected void execute(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        if (args.length > 1) {
            System.out.println("Введен не нужный аргумент. Команда приведена к базовой команде < reorder >.");
        }
        receiver.reorder();
    }
}
