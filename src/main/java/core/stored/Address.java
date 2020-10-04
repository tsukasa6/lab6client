package core.stored;

import java.io.Serializable;

public class Address implements Serializable {
    private String street; // Строка не может быть пустой, Поле не может быть null

    public Address(String street) {
        this.street = street;
    }

    public Address() {

    }

    public String getStreet() {
        return this.street;
    }

    public void setStreet(String street) {
        this.street = street;
    }
}
