package core.commands.concrete;

import core.commands.Command;
import core.commands.Receiver;

import java.io.IOException;

public class AddIfMin extends Command {
    private static final long serialVersionUID = 32L;
    transient private Receiver receiver;

    public AddIfMin(Receiver receiver) {
        this.receiver = receiver;
    }

    public AddIfMin() {

    }

    @Override
    protected void writeInfo() {
        System.out.println("Команда < add_if_min {element} > : [добавляет новый элемент в коллекцию, если его значение меньше, чем у наименьшего элемента этой коллекции];");
    }

    @Override
    protected void execute(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        if (args.length > 1) {
            System.out.println("Введен не нужный аргумент. Команда приведена к базовой команде < add_if_min >.");
        }
        receiver.add_if_min();
    }
}
