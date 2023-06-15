package Client;

import java.io.*;
import java.net.Socket;

public class Client {
    private Socket socket;
    private PrintWriter out;
    private ObjectInputStream inObj;
    private String name;

    public Client(String host, int port) {
        try {
            socket = new Socket(host, port);

            out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())),true);
            inObj = new ObjectInputStream(socket.getInputStream());

            System.out.println("Successfully connected.");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }

    public boolean isConnected() {
        return socket != null;
    }

    public void closeSession() {
        try {
            sendMessage("stop");

            socket.close();
            out.close();
            inObj.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void sendMessage(String str) {
        out.println(str);
    }

    public Object recvObject() {
        try {
            return inObj.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}