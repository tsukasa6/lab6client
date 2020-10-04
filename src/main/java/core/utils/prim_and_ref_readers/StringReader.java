package core.utils.prim_and_ref_readers;

import java.util.Scanner;

public class StringReader {
    public static String read(String msgToConsole, boolean canBeNull) {
        Scanner in = new Scanner(System.in);
        System.out.println(msgToConsole);
        String readString = in.nextLine().trim();

        while (!canBeNull && readString.equals("")) {
            System.out.println("Данное поле не может быть пустым. " + msgToConsole);
            readString = in.nextLine().trim();
        }

        if (canBeNull && readString.equals("")) {
            readString = null;
        }

        while (!canBeNull && readString.length() > 744) {
            System.out.println("Длина строки не может превышать 743 символа. Попробуйте снова: ");
            readString = in.nextLine().trim();
        }

        return readString;
    }
}
