package core.commands.concrete;

import core.commands.Command;
import core.commands.Receiver;

import java.io.IOException;

public class MaxByCreationDate extends Command {
    private static final long serialVersionUID = 32L;
    transient private Receiver receiver;

    public MaxByCreationDate(Receiver receiver) {
        this.receiver = receiver;
    }

    public MaxByCreationDate() {

    }

    @Override
    protected void writeInfo() {
        System.out.println("Команда < max_by_creation_date > : [выводит объект из коллекции, значение поля creationDate которого является максимальным];");
    }

    @Override
    protected void execute(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        if (args.length > 1) {
            System.out.println("Введен не нужный аргумент. Команда приведена к базовой команде < max_by_creation_date >.");
        }
        receiver.max_by_creation_date();
    }
}
