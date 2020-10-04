package core.commands.concrete;

import core.commands.Command;
import core.commands.Receiver;

import java.io.IOException;

public class CountGreaterThanSalary extends Command {
    private static final long serialVersionUID = 32L;
    transient private Receiver receiver;

    public CountGreaterThanSalary(Receiver receiver) {
        this.receiver = receiver;
    }

    public CountGreaterThanSalary() {

    }

    @Override
    protected void writeInfo() {
        System.out.println("Команда < count_greater_than_salary salary > : [выводит количество элементов, значение поля salary которых больше заданного];");
    }

    @Override
    protected void execute(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        if (args.length == 2) {
            receiver.count_greater_than_salary(args[1]);
        } else {
            System.out.println("Некорректное количество аргументов. Для справки напишите команду < help >.");
        }
    }
}
