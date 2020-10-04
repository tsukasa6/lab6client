package core.commands;

import core.client.Sender;
import core.client.SocketReceiver;
import core.commands.concrete.*;
import core.commands.serialized.SerializedArgumentCommand;
import core.commands.serialized.SerializedCombinedCommand;
import core.commands.serialized.SerializedObjectCommand;
import core.commands.serialized.SerializedSimplyCommand;
import core.stored.Worker;
import core.utils.WorkerCreator;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;

public class Receiver {
    private final Invoker invoker;
    private final Sender sender;
    private final SocketChannel socketChannel;
    private final Integer delay;
    private final WorkerCreator workerCreator;

    public Receiver(Invoker invoker, Sender sender, SocketChannel socketChannel, Integer delay, WorkerCreator workerCreator) {
        this.invoker = invoker;
        this.sender = sender;
        this.socketChannel = socketChannel;
        this.delay = delay;
        this.workerCreator = workerCreator;
    }

    public void help() {
        invoker.getCommandMap().forEach((name, command) -> command.writeInfo());
    }

    public void info() throws IOException, InterruptedException, ClassNotFoundException {
        sender.sendObject(new SerializedSimplyCommand(new Info()));
        Thread.sleep(delay);
        SocketReceiver.receive(socketChannel);
    }

    public void show() throws IOException, InterruptedException, ClassNotFoundException {
        sender.sendObject(new SerializedSimplyCommand(new Show()));
        Thread.sleep(delay);
        SocketReceiver.receive(socketChannel);
    }

    public void add() throws IOException, InterruptedException, ClassNotFoundException {
        sender.sendObject(new SerializedObjectCommand(new Add(), workerCreator.createWorker()));
        Thread.sleep(delay);
        SocketReceiver.receive(socketChannel);
    }

    public void update(String id) throws IOException, InterruptedException, ClassNotFoundException {
        sender.sendObject(new SerializedCombinedCommand(new Update(), workerCreator.createWorker(), id));
        Thread.sleep(delay);
        SocketReceiver.receive(socketChannel);
    }

    public void remove_by_id(String id) throws IOException, ClassNotFoundException, InterruptedException {
        sender.sendObject(new SerializedArgumentCommand(new RemoveById(), id));
        Thread.sleep(delay);
        SocketReceiver.receive(socketChannel);
    }

    public void clear() throws IOException, InterruptedException, ClassNotFoundException {
        sender.sendObject(new SerializedSimplyCommand(new Clear()));
        Thread.sleep(delay);
        SocketReceiver.receive(socketChannel);
    }

    public void exit() throws IOException {
        socketChannel.close();
        System.out.println("Завершение работы клиента.");
        System.exit(0);
    }

    public void execute_script(String path) {
        String line;
        String command;
        ArrayList<String> params = new ArrayList<>();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(path))) {
            while ((line = bufferedReader.readLine()) != null) {
                if (line.split(" ")[0].matches("add|update|remove_last")) {
                    params.clear();
                    command = line;
                    for (int i = 0; i < 11; i++) {
                        if (line != null) {
                            line = bufferedReader.readLine();
                            params.add(line);
                        } else {
                            System.out.println("Не хватает параметров для создания объекта.");
                            break;
                        }
                    }

                    Worker worker = workerCreator.createWorkerFromScript(params);
                    
                    if (worker != null) {
                        switch (command.split(" ")[0]) {
                            case "add":
                                sender.sendObject(new SerializedObjectCommand(new Add(), worker));
                                Thread.sleep(delay);
                                SocketReceiver.receive(socketChannel);
                                break;
                            case "update":
                                sender.sendObject(new SerializedCombinedCommand(new Update(), workerCreator.createWorker(), command.split(" ")[1]));
                                Thread.sleep(delay);
                                SocketReceiver.receive(socketChannel);
                                break;
                            case "remove_last":
                                sender.sendObject(new SerializedObjectCommand(new RemoveLast(), worker));
                                Thread.sleep(delay);
                                SocketReceiver.receive(socketChannel);
                                break;
                        }
                    }

                } else if ((line.split(" ")[0].equals("execute_script")) && line.split(" ")[1].equals(ExecuteScript.getPath())) {
                    System.out.println("Невозможно рекурсивно выполнить скрипт.");
                } else invoker.execute(line.split(" "));
            }
        } catch (IOException | ClassNotFoundException | InterruptedException e) {
            System.out.println("Ошибка. " + e.getMessage());
        }
    }

    public void add_if_min() throws IOException, InterruptedException, ClassNotFoundException {
        sender.sendObject(new SerializedObjectCommand(new AddIfMin(), workerCreator.createWorker()));
        Thread.sleep(delay);
        SocketReceiver.receive(socketChannel);
    }

    public void count_greater_than_salary(String salary) throws IOException, InterruptedException, ClassNotFoundException {
        sender.sendObject(new SerializedArgumentCommand(new CountGreaterThanSalary(), salary));
        Thread.sleep(delay);
        SocketReceiver.receive(socketChannel);
    }

    public void remove_last() throws IOException, ClassNotFoundException, InterruptedException {
        sender.sendObject(new SerializedObjectCommand(new RemoveLast(), workerCreator.createWorker()));
        Thread.sleep(delay);
        SocketReceiver.receive(socketChannel);
    }

    public void max_by_creation_date() throws IOException, ClassNotFoundException, InterruptedException {
        sender.sendObject(new SerializedSimplyCommand(new MaxByCreationDate()));
        Thread.sleep(delay);
        SocketReceiver.receive(socketChannel);
    }

    public void print_descending() throws IOException, ClassNotFoundException, InterruptedException {
        sender.sendObject(new SerializedObjectCommand(new PrintDescending(), workerCreator.createWorker()));
        Thread.sleep(delay);
        SocketReceiver.receive(socketChannel);
    }

    public void reorder() throws IOException, ClassNotFoundException, InterruptedException {
        sender.sendObject(new SerializedSimplyCommand(new Reorder()));
        Thread.sleep(delay);
        SocketReceiver.receive(socketChannel);
    }
}
