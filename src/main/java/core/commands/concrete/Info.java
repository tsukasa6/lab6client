package core.commands.concrete;

import core.commands.Command;
import core.commands.Receiver;

import java.io.IOException;

public class Info extends Command {
    private static final long serialVersionUID = 32L;
    transient private Receiver receiver;

    public Info(Receiver receiver) {
        this.receiver = receiver;
    }

    public Info() {

    }

    @Override
    protected void writeInfo() {
        System.out.println("Команда < info > : [выводит в стандартный поток вывода информацию о коллекции];");
    }

    @Override
    protected void execute(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        if (args.length > 1) {
            System.out.println("Введен не нужный аргумент. Команда приведена к базовой команде info.");
        }
        receiver.info();
    }
}
