package core.utils.enum_readers;

import core.stored.Status;

import java.util.Arrays;
import java.util.Scanner;

public class StatusReader {
    public static boolean checkExists(String status0) {
        return Arrays.stream(Status.values()).anyMatch((status -> status.name().equals(status0)));
    }

    public static Status read(String msgToClient, boolean canBeNull) {
        Scanner in = new Scanner(System.in, "UTF-8");
        System.out.println(msgToClient + " Выберите статус работника (" + Arrays.asList(Status.values()) + "): ");
        String statusReader = in.nextLine().trim();

        if ((!checkExists(statusReader)) && !canBeNull && !statusReader.equals("") || !canBeNull && statusReader.equals("") || canBeNull && !statusReader.equals("")) {
            while (!checkExists(statusReader)) {
                System.out.println("Вы ввели несуществующее значение из представленных. Попробуйте снова: ");
                statusReader = in.nextLine().trim();
                checkExists(statusReader);
            }
        }

        if (canBeNull && statusReader.equals("")) return null;

        return Enum.valueOf(Status.class, statusReader);
    }
}
