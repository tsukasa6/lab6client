package core.utils;

import core.stored.*;
import core.utils.enum_readers.OrganizationTypeReader;
import core.utils.enum_readers.StatusReader;
import core.utils.prim_and_ref_readers.PrimDoubleReader;
import core.utils.prim_and_ref_readers.RefLongReader;
import core.utils.prim_and_ref_readers.StringReader;

import java.util.ArrayList;

public class WorkerCreator {
    public static Worker createWorker() {
        String name = StringReader.read("Введите имя работника: ", false);
        Long x = RefLongReader.read("Введите x: ", -548, "MIN", false);
        double y = PrimDoubleReader.read("Введите y: ");
        Long salary = RefLongReader.read("Введите зарплату работника: ", 0, "MIN", false);
        Status status = StatusReader.read("Введите статус работника.", true);

        return new Worker(name, new Coordinates(x, y), salary, status, createOrganization());
    }

    public static Organization createOrganization() {
        String fullName = StringReader.read("Введите полное имя работника: ", false);
        OrganizationType type = OrganizationTypeReader.read("Введите тип организации.", true);
        String officialAddress = StringReader.read("Введите адрес работника: ", false);

        return new Organization(fullName, type, new Address(officialAddress));
    }

    public static Worker createWorkerFromScript(ArrayList<String> params) {
        if (validateArray(params)) {
            Status status = null;
            if (!params.get(4).isEmpty()) {
                status = Status.valueOf(params.get(4));
            }
            return new Worker(params.get(0),
                    new Coordinates(Long.parseLong(params.get(1)), Double.parseDouble(params.get(2))),
                    Long.parseLong(params.get(3)),
                    status,
                    new Organization(params.get(5), OrganizationType.valueOf(params.get(6)), new Address(params.get(7))));
        } else System.out.println("Один из параметров не соответствует требованиям.");

        return null;
    }

    private static boolean validateArray(ArrayList<String> params) {
        try {
            return (!params.get(0).isEmpty()
                    && Long.parseLong(params.get(1)) > -548
                    && Double.parseDouble(params.get(2)) > -100
                    && Long.parseLong(params.get(3)) > 0
                    && StatusReader.checkExists(params.get(4))
                    && !params.get(5).isEmpty()
                    && OrganizationTypeReader.checkExists(params.get(6))
                    && !params.get(7).isEmpty());
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
