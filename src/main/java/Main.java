import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        try {
            ConsoleManager consoleManager = new ConsoleManager();
            consoleManager.startInteractiveMode(args[0], args[1], args[2]);
        } catch (ArrayIndexOutOfBoundsException ex) {
            System.out.println("Некорретное число аргументов. \n" +
                    "Введите 3 аргумента: адрес, порт, задержка.");
        }
    }
}
