package core.client;

import core.commands.serialized.SerializedMessage;

import java.io.IOException;

public class Decrypting {
    static void decrypt(Object o) throws IOException {
        if (o instanceof SerializedMessage) {
            SerializedMessage serializedMessage = (SerializedMessage) o;
            System.out.println(((SerializedMessage) o).getMessage());
        }
    }
}
