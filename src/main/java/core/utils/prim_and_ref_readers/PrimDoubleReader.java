package core.utils.prim_and_ref_readers;

import java.util.Scanner;

public class PrimDoubleReader {
    public static double read(String msgToConsole) {
        System.out.println(msgToConsole);
        Scanner in = new Scanner(System.in);
        double readDouble = 0;
        boolean end = false;

        while (!end) {
            try {
                readDouble = Double.parseDouble(in.nextLine().trim());
                break;
            } catch (NumberFormatException e) {
                System.out.println("Необходимо ввести число типа double. Попробуйте снова: ");
            }
        }

        return readDouble;
    }
}
