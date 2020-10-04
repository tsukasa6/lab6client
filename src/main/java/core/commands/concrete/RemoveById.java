package core.commands.concrete;

import core.commands.Command;
import core.commands.Receiver;

import java.io.IOException;

public class RemoveById extends Command {
    private static final long serialVersionUID = 32L;
    transient private Receiver receiver;

    public RemoveById(Receiver receiver) {
        this.receiver = receiver;
    }

    public RemoveById() {

    }

    @Override
    protected void writeInfo() {
        System.out.println("Команда < remove_by_id id > : [удаляет элемент из коллекции по его id];");
    }

    @Override
    protected void execute(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        if (args.length == 2) { receiver.remove_by_id(args[1]); }
        else { System.out.println("Некорректное количество аргументов. Для справки напишите < help >."); }
    }
}
