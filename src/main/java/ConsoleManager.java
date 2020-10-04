import core.client.Sender;
import core.client.Session;
import core.commands.Invoker;
import core.commands.Receiver;
import core.commands.concrete.*;
import core.utils.WorkerCreator;

import java.io.IOException;
import java.util.Scanner;

public class ConsoleManager {
    void startInteractiveMode(String hostName, String port, String delayArg) throws IOException {
        Session session = null;
        int delay = 0;

        try {
            session = new Session(hostName, Integer.parseInt(port));
            session.startSession();
            delay = Integer.parseInt(delayArg);
            if (delay < 100) delay = 100; // Минимальная задержка - 100.
        } catch (NumberFormatException ex) {
            System.out.println("Один из аргументов не соотвествует требованиям. \n" +
                    "Адрес (имя хоста) - текстовое значение, Порт и Задержка - целочисленные значения.");
            System.exit(0);
        } catch (InterruptedException ex) {
            System.out.println(ex.getMessage());
        }

        Sender sender = new Sender(session);

        WorkerCreator workerCreator = new WorkerCreator();
        Invoker invoker = new Invoker();
        Receiver receiver = new Receiver(invoker, sender, session.getSocketChannel(), delay, workerCreator);

        invoker.register("add", new Add(receiver));
        invoker.register("add_if_min", new AddIfMin(receiver));
        invoker.register("clear", new Clear(receiver));
        invoker.register("count_greater_than_salary", new CountGreaterThanSalary(receiver));
        invoker.register("execute_script", new ExecuteScript(receiver));
        invoker.register("exit", new Exit(receiver));
        invoker.register("help", new Help(receiver));
        invoker.register("info", new Info(receiver));
        invoker.register("max_by_creation_date", new MaxByCreationDate(receiver));
        invoker.register("print_descending", new PrintDescending(receiver));
        invoker.register("remove_by_id", new RemoveById(receiver));
        invoker.register("remove_last", new RemoveLast(receiver));
        invoker.register("reorder", new Reorder(receiver));
        invoker.register("show", new Show(receiver));
        invoker.register("update", new Update(receiver));

        try(Scanner scanner = new Scanner(System.in)) {
            while (scanner.hasNextLine()) {
                invoker.execute(scanner.nextLine().trim().split(" "));
            }
        }

        session.closeSession();
    }
}
