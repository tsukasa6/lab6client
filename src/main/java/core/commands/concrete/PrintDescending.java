package core.commands.concrete;

import core.commands.Command;
import core.commands.Receiver;

import java.io.IOException;

public class PrintDescending extends Command {
    private static final long serialVersionUID = 32L;
    transient private Receiver receiver;

    public PrintDescending(Receiver receiver) {
        this.receiver = receiver;
    }

    public PrintDescending() {

    }

    @Override
    protected void writeInfo() {
        System.out.println("Команда < print_descending > : [выводит элементы коллекции в порядке убывания];");
    }

    @Override
    protected void execute(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        if (args.length > 1) {
            System.out.println("Введен не нужный аргумент. Команда приведена к базовой команде < print_descending >.");
        }
        receiver.print_descending();
    }
}
