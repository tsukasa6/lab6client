package core.utils.prim_and_ref_readers;

import java.util.Scanner;

public class RefLongReader {
    public static Long read(String msgToConsole, int limit, String type, boolean canBeNull) {
        System.out.println(msgToConsole);
        Scanner in = new Scanner(System.in);
        long readLong = 0;
        boolean end = false;

        while (!end && !canBeNull) {
            try {
                readLong = Long.parseLong(in.nextLine().trim());
                switch (type) {
                    case ("MIN"):
                        if (readLong > limit) end = true;
                        else System.out.println("Вы ввели неподходящее значение. Оно должно быть больше " + limit + ". Попробуйте снова.");
                        break;
                    case ("MAX"):
                        if (readLong < limit) end = true;
                        else System.out.println("Вы ввели неподходящее значение. Оно должно быть меньше " + limit + ". Попробуйте снова.");
                        break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Необходимо ввести число. Попробуйте снова: ");
            }
        }

        return readLong;
    }
}
