package View;

import Mode.Model;

import javax.swing.*;
import java.awt.*;

public class Menu extends JPanel {
    private JButton jButton1;
    private JButton jButton2;
    private JButton jButton3;
    public Menu() {

        int buttonWidth = Config.WIDTH * Config.cellSIZE / 2;
        int buttonHeight = Config.HEIGHT * Config.cellSIZE / 12;

        setBounds(0, 0, Config.WIDTH * Config.cellSIZE, (Config.HEIGHT + 1) * Config.cellSIZE);
        setBackground(Config.MENU_COLOR);

        jButton1 = new JButton("START GAME");
        jButton1.setBounds(Config.WIDTH * Config.cellSIZE / 4,Config.HEIGHT * Config.cellSIZE / 3,
                buttonWidth, buttonHeight);
        add(jButton1);
        jButton1.setBackground(Color.WHITE);

        jButton2 = new JButton("SCORES");
        jButton2.setBounds(Config.WIDTH * Config.cellSIZE / 4,Config.HEIGHT * Config.cellSIZE / 3 + (int)(buttonHeight * 1.2),
                buttonWidth, buttonHeight);
        add(jButton2);
        jButton2.setBackground(Color.WHITE);

        jButton3 = new JButton("EXIT");
        jButton3.setBounds(Config.WIDTH * Config.cellSIZE / 4,Config.HEIGHT * Config.cellSIZE / 3 + (int)(buttonHeight * 2.4),
                buttonWidth, buttonHeight);
        add(jButton3);
        jButton3.setBackground(Color.WHITE);

        setLayout(null);
    }

    public JButton getjButton1() {
        return jButton1;
    }
    public JButton getjButton2() {
        return jButton2;
    }
    public JButton getjButton3() {
        return jButton3;
    }
    public void showStartMenu() {
        setVisible(true);
    }

    public void hideStartMenu() {
        setVisible(false);
    }
}
