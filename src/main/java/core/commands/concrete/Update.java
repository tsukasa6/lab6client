package core.commands.concrete;

import core.commands.Command;
import core.commands.Receiver;

import java.io.IOException;

public class Update extends Command {
    private static final long serialVersionUID = 32L;
    transient private Receiver receiver;

    public Update(Receiver receiver) {
        this.receiver = receiver;
    }

    public Update() {

    }

    @Override
    protected void writeInfo() {
        System.out.println("Команда < update {id} > : [обновляет значение элемента коллекции, id которого равен заданному].");
    }

    @Override
    protected void execute(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        if (args.length == 2) {
            receiver.update(args[1]);
        } else {
            System.out.println("Некорректное количество аргументов. Для справки напишите < help >.");
        }
    }
}
