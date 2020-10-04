package core.commands.concrete;

import core.commands.Command;
import core.commands.Receiver;

import java.io.IOException;

public class Show extends Command {
    private static final long serialVersionUID = 32L;
    transient private Receiver receiver;

    public Show(Receiver receiver) {
        this.receiver = receiver;
    }

    public Show() {

    }

    @Override
    protected void writeInfo() {
        System.out.println("Команда < show > : [выводит все элементы коллекции в строковом представлении];");
    }

    @Override
    protected void execute(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        if (args.length > 1) {
            System.out.println("Введен не нужный аргумент. Команда приведена к базовой команде show.");
        }
        receiver.show();
    }
}
