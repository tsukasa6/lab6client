package core.client;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class SocketReceiver {
    public static void receive(SocketChannel socketChannel) throws IOException, ClassNotFoundException {
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024*1024);
        socketChannel.read(byteBuffer);

        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteBuffer.array());
        ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);

        Decrypting.decrypt(objectInputStream.readObject());
    }
}
