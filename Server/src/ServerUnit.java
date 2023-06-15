
import java.io.*;
import java.net.Socket;
import java.util.*;

class ServerUnit implements Runnable {
    private Thread myThread;
    private Socket socket;
    private static BufferedReader in;
    private static ObjectOutputStream outObj;

    public ServerUnit(Socket socket) throws IOException {
        this.socket = socket;

        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        outObj = new ObjectOutputStream(socket.getOutputStream());

        myThread = new Thread(this);
        myThread.start();
    }
    @Override
    public void run() {
        try {
            while (true) {
                String msg = in.readLine();
                System.out.println(msg);
                if (msg.equals("stop")) {
                    closeSession();
                    break;
                }
                if (msg.equals("U")) {
                    updateStatistic();
                } else {
                    addNewResult(msg);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void closeSession() {
        try {
            socket.close();
            in.close();
            outObj.close();

            Server.serverList.remove(this);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void updateStatistic() {
        LinkedList<Result> statistic = Server.getStatistic();
        System.out.println(statistic);

        ArrayList<String> strings = new ArrayList<>();
        ArrayList<Integer> integers = new ArrayList<>();

        for (Result result : statistic) {
            strings.add(result.name());
            integers.add(result.scores());
        }
        sendObject(strings);
        sendObject(integers);
    }

    private void addNewResult(String msg) {
        Boolean isNewRecord = Server.addStatistic(msg);
        sendObject(isNewRecord);
    }

    public void sendObject(Object obj) {
        try {
            outObj.writeObject(obj);
            outObj.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}