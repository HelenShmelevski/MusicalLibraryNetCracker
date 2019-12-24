package sample;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ClientSocket {
    private Socket socket;
    private ObjectOutputStream writer;
    private ObjectInputStream reader;

    public ClientSocket(){ }

    public void connect(String host, int port) {
        try {
            socket = new Socket(host, port);
            writer = new ObjectOutputStream(socket.getOutputStream());
            reader = new ObjectInputStream(socket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendObject(Object object)
    {
        try {
            writer.writeObject(object);
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Object getData()
    {
        try {
            return reader.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void disconnect() {
        try {
            reader.close();
            writer.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
