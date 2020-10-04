package core.commands.concrete;

import core.commands.Command;
import core.commands.Receiver;

import java.io.IOException;

public class RemoveLast extends Command {
    private static final long serialVersionUID = 32L;
    transient private Receiver receiver;

    public RemoveLast(Receiver receiver) {
        this.receiver = receiver;
    }

    public RemoveLast() {

    }

    @Override
    protected void writeInfo() {
        System.out.println("Команда < remove_last > : [удаляет последний элемент из коллекции];");
    }

    @Override
    protected void execute(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        if (args.length > 1) {
            System.out.println("Введен не нужный аргумент. Команда приведена к базовой команде remove_first.");
        }
        receiver.remove_last();
    }
}
