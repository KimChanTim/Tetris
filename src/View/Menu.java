package View;

import Mode.Model;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Locale;
import java.util.Map;

public class Menu extends JPanel {
    private final JButton jButton1;
    private final JButton jButton2;
    private final JButton jButton3;
    private final JButton jButton4;
    private final JButton jButton5;
    private final JButton jButton6;
    public JTextField jTextField;
    public JLabel label1;
    public JLabel label2;
    public JLabel label3;
    private final JPanel statisticPanel;

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
        jButton1.setVisible(false);

        jButton2 = new JButton("SCORES");
        jButton2.setBounds(Config.WIDTH * Config.cellSIZE / 4,Config.HEIGHT * Config.cellSIZE / 3 + (int)(buttonHeight * 1.2),
                buttonWidth, buttonHeight);
        add(jButton2);
        jButton2.setBackground(Color.WHITE);
        jButton2.setVisible(false);

        jButton3 = new JButton("EXIT");
        jButton3.setBounds(Config.WIDTH * Config.cellSIZE / 4,Config.HEIGHT * Config.cellSIZE / 3 + (int)(buttonHeight * 2.4),
                buttonWidth, buttonHeight);
        add(jButton3);
        jButton3.setBackground(Color.WHITE);

        jButton4 = new JButton("RESET");
        jButton4.setBounds(Config.cellSIZE,(Config.HEIGHT - 1) * Config.cellSIZE,
                Config.cellSIZE * 4, Config.cellSIZE);
        add(jButton4);
        jButton4.setBackground(Color.WHITE);
        jButton4.setVisible(false);

        jButton5 = new JButton("CLOSE");
        jButton5.setBounds(Config.cellSIZE * 5,(Config.HEIGHT - 1) * Config.cellSIZE,
                Config.cellSIZE * 4, Config.cellSIZE);
        add(jButton5);
        jButton5.setBackground(Color.WHITE);
        jButton5.setVisible(false);

        jButton6 = new JButton("CONTINUE");
        jButton6.setBounds(Config.WIDTH * Config.cellSIZE / 4,Config.HEIGHT * Config.cellSIZE / 3 + (int)(buttonHeight * 1.2),
                buttonWidth, buttonHeight);
        add(jButton6);
        jButton6.setBackground(Color.WHITE);

        label2 = new JLabel();
        label2.setFont(new Font("Button.font", Font.BOLD, 20));
        label2.setBounds(Config.WIDTH * Config.cellSIZE / 4 + 3, Config.cellSIZE * 3,
                Config.WIDTH * Config.cellSIZE, Config.cellSIZE);
        add(label2);
        label2.setVisible(false);

        label3 = new JLabel();
        label3.setFont(new Font("Button.font", Font.BOLD, 20));
        label3.setBounds(Config.WIDTH * Config.cellSIZE / 4 + 3, Config.cellSIZE * 4,
                Config.WIDTH * Config.cellSIZE, Config.cellSIZE);
        add(label3);
        label3.setVisible(false);

        statisticPanel = new JPanel();
        statisticPanel.setBounds(Config.cellSIZE, Config.cellSIZE,
                (Config.WIDTH - 2) * Config.cellSIZE , (Config.HEIGHT - 2) * Config.cellSIZE);
        statisticPanel.setBackground(Color.WHITE);
        add(statisticPanel);
        statisticPanel.setVisible(false);

        initStartMenu();

        setLayout(null);
    }

    public void initStartMenu() {
        jTextField = new JTextField();
        add(jTextField);
        jTextField.setBounds(Config.WIDTH * Config.cellSIZE / 4, Config.cellSIZE * 9 + 10,
                Config.WIDTH * Config.cellSIZE / 2, Config.cellSIZE);
        jTextField.setVisible(true);
        jTextField.setText("PLAYER");

        label1 = new JLabel("ENTER NICKNAME");
        label1.setFont(new Font("Button.font", Font.BOLD, 20));
        label1.setBounds(Config.WIDTH * Config.cellSIZE / 6 + 10, Config.cellSIZE * 8,
                Config.WIDTH * Config.cellSIZE, Config.cellSIZE);
        add(label1);
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
    public JButton getjButton4() {
        return jButton4;
    }
    public JButton getjButton5() {
        return jButton5;
    }
    public JButton getjButton6() {
        return jButton6;
    }
    public void showStartMenu(Boolean isNewRecord, Integer scores) {
        if (isNewRecord) {
            label2.setText("NEW RECORD");
            label2.setVisible(true);
        }
        label3.setText("SCORES " + scores.toString());
        label3.setVisible(true);
        setVisible(true);
    }

    public void hideStartMenu() {
        setVisible(false);
    }

    public void showStatisticPanel(ArrayList<String> strings, ArrayList<Integer> integers) {
        label2.setVisible(false);
        label3.setVisible(false);

        jButton1.setVisible(false);
        jButton2.setVisible(false);
        jButton3.setVisible(false);
        jButton4.setVisible(true);
        jButton5.setVisible(true);

        statisticPanel.setLayout(new BoxLayout(statisticPanel, BoxLayout.Y_AXIS));

        for (int i = 0; i < strings.size(); i++) {
            JLabel label = new JLabel(Integer.toString(i + 1) + ") " + strings.get(i) + " " + integers.get(i).toString());
            label.setBounds(Config.cellSIZE, Config.cellSIZE + i * Config.cellSIZE,
                    (Config.WIDTH - 2) * Config.cellSIZE , (Config.HEIGHT - 2) * Config.cellSIZE);
            statisticPanel.add(label);
        }
        statisticPanel.setVisible(true);

    }
    public void hideStatisticPanel() {
        statisticPanel.removeAll();
        statisticPanel.setVisible(false);
        jButton1.setVisible(true);
        jButton2.setVisible(true);
        jButton3.setVisible(true);
        jButton4.setVisible(false);
        jButton5.setVisible(false);
    }
}
