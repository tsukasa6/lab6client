package core.commands.concrete;

import core.commands.Command;
import core.commands.Receiver;

import java.io.IOException;

public class ExecuteScript extends Command {
    private static final long serialVersionUID = 32L;
    transient private Receiver receiver;
    private static String path;

    public ExecuteScript(Receiver receiver) {
        this.receiver = receiver;
    }

    public ExecuteScript() {}

    @Override
    protected void writeInfo() {
        System.out.println("Команда < execute_script file_name > : [считывает и исполняет скрипт из указанного файла];");
    }

    @Override
    protected void execute(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        try {
            if (args.length == 2) { path = args[1]; receiver.execute_script(args[1]); }
            else { System.out.println("Некорректное количество аргументов. Для справки напишите < help >."); }
        } catch (StackOverflowError ex) {
            System.out.println("Ошибка! Обнаружен выход за пределы стека.");
        }
    }

    public static String getPath() {
        return path;
    }
}
