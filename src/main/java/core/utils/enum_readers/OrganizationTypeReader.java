package core.utils.enum_readers;

import core.stored.OrganizationType;

import java.util.Arrays;
import java.util.Scanner;

public class OrganizationTypeReader {
    public static boolean checkExists(String orgType) {
        return Arrays.stream(OrganizationType.values()).anyMatch((organizationType -> organizationType.name().equals(orgType)));
    }

    public static OrganizationType read(String msgToClient, boolean canBeNull) {
        Scanner in = new Scanner(System.in, "UTF-8");
        System.out.println(msgToClient + " Выберите тип организации (" + Arrays.asList(OrganizationType.values()) + "): ");
        String orgTypeReader = in.nextLine().trim();

        if ((!checkExists(orgTypeReader)) && !canBeNull && !orgTypeReader.equals("") || !canBeNull && orgTypeReader.equals("") || canBeNull && !orgTypeReader.equals("")) {
            while (!checkExists(orgTypeReader)) {
                System.out.println("Вы ввели несуществующее значение из представленных. Попробуйте снова: ");
                orgTypeReader = in.nextLine().trim();
                checkExists(orgTypeReader);
            }
        }

        if (canBeNull && orgTypeReader.equals("")) return null;

        return Enum.valueOf(OrganizationType.class, orgTypeReader);
    }
}
