import Controller.Controller;
import View.Window;

public class Main {
    public static void main(String[] args) {
        try {
            Controller controller = new Controller();
        }
        catch (Exception e) {
            e.printStackTrace(System.err);
        }
    }
}
