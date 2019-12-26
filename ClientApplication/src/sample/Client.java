package sample;

import models.Artist;
import models.CommandType;
import models.Genre;
import models.ModelType;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Client {

    private static Socket clientSocket; //сокет для общения
    private static BufferedReader reader; //  ридер читающий с консоли
    private static ObjectInputStream in; // поток чтения из сокета
    private static ObjectOutputStream out; // поток записи в сокет

    public static void main(String[] args) {
        try {
            try {

                clientSocket = new Socket("localhost", 1234);
                reader = new BufferedReader(new InputStreamReader(System.in));
                out = new ObjectOutputStream(clientSocket.getOutputStream());
                in = new ObjectInputStream(clientSocket.getInputStream());

                out.writeObject(CommandType.GET_ALL);
                out.flush();

                out.writeObject(ModelType.GENRE);
                out.flush();

                out.writeObject(CommandType.ADD);
                out.flush();

                out.writeObject(ModelType.ARTIST);
                out.flush();

                Genre[] genres = (Genre[]) in.readObject();
                Artist[] artists = (Artist[]) in.readObject();

            } finally {
                System.out.println("Клиент был закрыт...");
                clientSocket.close();
                in.close();
                out.close();
            }
        } catch (Exception e) {
            System.err.println(e);
        }

    }
}
