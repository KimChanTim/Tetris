
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;


public class Server {
    public static LinkedList<ServerUnit> serverList = new LinkedList<>();
    private static LinkedList<Result> statistic;
    private static final int LIST_SIZE = 30;
    private static final Object mutex = new Object();

    public Server(int port) {
        try (ServerSocket server = new ServerSocket(port)) {
            statistic = new LinkedList<>();

            while (true) {
                Socket socket = server.accept();
                try {
                    serverList.add(new ServerUnit(socket));
                } catch (IOException e) {
                    e.printStackTrace();
                    socket.close();
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static LinkedList<Result> getStatistic() {
        synchronized (mutex) {
            return statistic;
        }
    }

    public static boolean addStatistic(String msg) {
        String[] strings = msg.split(" ");
        Integer value = Integer.valueOf(strings[1]);

        synchronized (mutex) {
            int i;
            for (i = 0; i < statistic.size() && i < LIST_SIZE; i++) {
                if (statistic.get(i).scores() < value) {
                    statistic.add(i, new Result(strings[0], value));
                    return i == 0;
                }
            }
            if (statistic.size() < LIST_SIZE) {
                statistic.add(new Result(strings[0], value));
                return i == 0;
            }
        }
        return false;
    }
}