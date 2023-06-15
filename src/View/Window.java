package View;

import javax.swing.*;
import java.awt.*;



public class Window extends JFrame implements Runnable {
    public Window() {
        initFrame();
        setFocusable(false);
        requestFocusInWindow();
    }

    @Override
    public void run() {
        repaint();
    }
    private void initFrame() {
        pack();
        Insets insets = getInsets();
        setSize(Config.WIDTH * Config.cellSIZE + insets.left + insets.right,
                (Config.HEIGHT + 1) * Config.cellSIZE + insets.top + insets.bottom);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Tetris");

        setLayout(null);
        setVisible(true);
    }

}
