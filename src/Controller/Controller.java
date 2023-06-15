package Controller;

import Client.Client;
import Mode.Model;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Controller {
    private final Model model;
    private final Client client;
    public Controller() {
        client = new Client("localhost", 5000);
        model = new Model(client);
        initButtons();
        model.getGameField().addKeyListener(new KeyReader());
    }
    private class KeyReader extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            super.keyPressed(e);

            switch (e.getKeyCode()) {
                case KeyEvent.VK_UP -> model.turnFigure();
                case KeyEvent.VK_DOWN -> model.moveFigure(0, 1);
                case KeyEvent.VK_LEFT -> model.moveFigure(-1, 0);
                case KeyEvent.VK_RIGHT -> model.moveFigure(1, 0);
                default -> model.moveFigure(0, -1);
            }
        }
    }

    private void initButtons() {
        model.getMenu().getjButton1().addActionListener(e -> {
            model.getMenu().hideStartMenu();
            model.startGame();
        });

        model.getMenu().getjButton2().addActionListener(e -> {
            model.updateStatistic();
        });

        model.getMenu().getjButton3().addActionListener(e -> {
            model.getWindow().setVisible(false);
            model.getWindow().dispose();
            client.closeSession();
            System.exit(0);
        });

        model.getMenu().getjButton4().addActionListener(e -> {
            model.updateStatistic();
        });

        model.getMenu().getjButton5().addActionListener(e -> {
            model.closeStatistic();
        });

        model.getMenu().getjButton6().addActionListener(e -> {
            client.setName(model.getMenu().jTextField.getText());
            model.getMenu().label1.setVisible(false);
            model.getMenu().jTextField.setVisible(false);

            model.getMenu().getjButton6().setVisible(false);
            model.getMenu().getjButton1().setVisible(true);
            model.getMenu().getjButton2().setVisible(true);
        });
    }
}
