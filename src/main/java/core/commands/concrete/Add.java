package core.commands.concrete;

import core.commands.Command;
import core.commands.Receiver;

import java.io.IOException;

public class Add extends Command {
    private static final long serialVersionUID = 32L;
    transient private Receiver receiver;

    public Add(Receiver receiver) {
        this.receiver = receiver;
    }

    public Add() {

    }

    @Override
    protected void writeInfo() {
        System.out.println("Команда < add > : [добавляет новый элемент в коллекцию];");
    }

    @Override
    protected void execute(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        if (args.length > 1) {
            System.out.println("Введен ненужный аргумент. Команда приведена к базовой команде < add >.");
        }
        receiver.add();
    }
}
