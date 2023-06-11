package Controller;

import View.Window;

public class Controller {
    public Controller() {
        Window window = new Window();
        javax.swing.SwingUtilities.invokeLater(window);
    }

}
