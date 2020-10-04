package core.commands.concrete;

import core.commands.Command;
import core.commands.Receiver;

import java.io.IOException;

public class Exit extends Command {
    private static final long serialVersionUID = 32L;
    transient private Receiver receiver;

    public Exit(Receiver receiver) {
        this.receiver = receiver;
    }

    public Exit() {

    }

    @Override
    protected void writeInfo() {
        System.out.println("Команда < exit > : [завершает программу (без сохранения в файл)];");
    }

    @Override
    protected void execute(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        if (args.length > 1) {
            System.out.println("Введен не нужный аргумент. Команда приведена к базовой команде < exit >.");
        }
        receiver.exit();
    }
}
