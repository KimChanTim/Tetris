import View.Window;

public class Main {
    public static void main(String[] args) {
        try {
            Window window = new Window();
            javax.swing.SwingUtilities.invokeLater(window);
        }
        catch (Exception e) {
            e.printStackTrace(System.err);
        }
    }
}
