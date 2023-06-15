package Controller;

import Mode.Model;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Controller {
    private Model model;
    public Controller() {
        model = new Model();
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

        });

        model.getMenu().getjButton3().addActionListener(e -> {
            model.getWindow().setVisible(false);
            model.getWindow().dispose();
            System.exit(0);
        });
    }
}
