package View;


import Mode.Coord;
import Mode.FigureModel;

import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import java.awt.*;
import java.awt.event.*;


public class Window extends JFrame implements Runnable, CellsState {
    private final Cell[][] cells;
    private FigureModel figure;
    private final Timer timer;
    private final Menu menu;
    private JTextField jTextField;
    private JTextArea jTextArea;
    private Integer scores;

    public Window() {
        cells = new Cell[Config.WIDTH][Config.HEIGHT];
        initFrame();
        initCells();

        menu = new Menu();
        addKeyListener(new KeyReader());
        setFocusable(false);
        requestFocusInWindow();
        timer = new Timer(200, new TimeAction());
        menu.showStartMenu();
    }

    public void startGame() {
        showCellsScores();
        timer.start();
        setFocusable(true);
        requestFocusInWindow();
        addFigure();
        scores = 0;
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

        jTextField = new JTextField();
        add(jTextField);
        jTextField.setBounds(0, Config.HEIGHT * Config.cellSIZE, Config.WIDTH * Config.cellSIZE, Config.cellSIZE);
        jTextField.setVisible(false);

        jTextArea = new JTextArea();
        add(jTextArea);
        jTextArea.setBounds(Config.WIDTH * Config.cellSIZE / 4,Config.HEIGHT * Config.cellSIZE / 3,
                Config.WIDTH * Config.cellSIZE / 2, Config.HEIGHT * Config.cellSIZE / 3);
        jTextArea.setVisible(false);

        setLayout(null);
        setVisible(true);
    }

    class Menu extends JPanel {
        public Menu() {
            int buttonWidth = Config.WIDTH * Config.cellSIZE / 2;
            int buttonHeight = Config.HEIGHT * Config.cellSIZE / 12;

            setBounds(0, 0, Config.WIDTH * Config.cellSIZE, (Config.HEIGHT + 1) * Config.cellSIZE);
            setBackground(Config.MENU_COLOR);

            JButton jButton1 = new JButton("START GAME");
            jButton1.setBounds(Config.WIDTH * Config.cellSIZE / 4,Config.HEIGHT * Config.cellSIZE / 3,
                    buttonWidth, buttonHeight);
            add(jButton1);
            jButton1.setBackground(Color.WHITE);

            JButton jButton2 = new JButton("ABOUT");
            jButton2.setBounds(Config.WIDTH * Config.cellSIZE / 4,Config.HEIGHT * Config.cellSIZE / 3 + (int)(buttonHeight * 1.2),
                    buttonWidth, buttonHeight);
            add(jButton2);
            jButton2.setBackground(Color.WHITE);

            JButton jButton3 = new JButton("SCORES");
            jButton3.setBounds(Config.WIDTH * Config.cellSIZE / 4,Config.HEIGHT * Config.cellSIZE / 3 + (int)(buttonHeight * 2.4),
                    buttonWidth, buttonHeight);
            add(jButton3);
            jButton3.setBackground(Color.WHITE);

            JButton jButton4 = new JButton("EXIT");
            jButton4.setBounds(Config.WIDTH * Config.cellSIZE / 4,Config.HEIGHT * Config.cellSIZE / 3 + (int)(buttonHeight * 3.6),
                    buttonWidth, buttonHeight);
            add(jButton4);
            jButton4.setBackground(Color.WHITE);

            JButton jButton5 = new JButton("CLOSE");
            jButton5.setBounds(Config.WIDTH * Config.cellSIZE / 4,2 * Config.HEIGHT * Config.cellSIZE / 3,
                    buttonWidth, buttonHeight);
            add(jButton5);
            jButton5.setBackground(Color.WHITE);

            jButton1.addActionListener(e -> {
                hideStartMenu();
                startGame();
            });
            jButton2.addActionListener(e -> {

            });
            jButton3.addActionListener(e -> {
                jTextArea.setVisible(true);
                jButton5.setVisible(true);
                jButton1.setVisible(false);
                jButton2.setVisible(false);
                jButton3.setVisible(false);
                jButton4.setVisible(false);
            });
            jButton4.addActionListener(e -> {
                Window.this.setVisible(false);
                Window.this.dispose();
                System.exit(0);
            });
            jButton5.addActionListener(e -> {
                jTextArea.setVisible(false);
                jButton5.setVisible(false);
                jButton1.setVisible(true);
                jButton2.setVisible(true);
                jButton3.setVisible(true);
                jButton4.setVisible(true);
            });
            jButton5.setVisible(false);
            setLayout(null);
            Window.this.add(this);
        }

        private void showStartMenu() {
            setVisible(true);
        }

        private void hideStartMenu() {
            setVisible(false);
        }
    }


    private void initCells() {
        for (int x = 0; x < Config.WIDTH; x++) {
            for (int y = 0; y < Config.HEIGHT; y++) {
                cells[x][y] = new Cell(x, y);
                add(cells[x][y]);
                cells[x][y].setVisible(false);
            }
        }
    }
    private void showCellsScores() {
        for (int x = 0; x < Config.WIDTH; x++) {
            for (int y = 0; y < Config.HEIGHT; y++) {
                cells[x][y].setColor(Config.GAME_COLOR);
                cells[x][y].setVisible(true);
            }
        }

        jTextField.setVisible(true);
        jTextField.setText("SCORES: 0");
    }
    private void hideCellsScores() {
        for (int x = 0; x < Config.WIDTH; x++) {
            for (int y = 0; y < Config.HEIGHT; y++) {
                cells[x][y].setVisible(false);
                cells[x][y].deOccupied();
            }
        }

        jTextField.setVisible(false);
    }

    private void addFigure() {
        figure = new FigureModel(this);
        if (!figure.canPlaceFigure()) {
            timer.stop();
            setFocusable(false);
            requestFocusInWindow();
            hideCellsScores();
            menu.showStartMenu();
        }
        showFigure();
    }

    private void showFigure() {
        for (Coord point : figure.getFigure().points) {
            setCellColor(figure.getCoord().x() + point.x(), figure.getCoord().y() + point.y(), figure.getColor());
        }
    }

    private void hideFigure() {
        for (Coord point : figure.getFigure().points) {
            setCellColor(figure.getCoord().x() + point.x(), figure.getCoord().y() + point.y(), Config.GAME_COLOR);
        }
    }

    private void setCellColor(int x, int y, Color color) {
        if (x < 0 || x >= Config.WIDTH) return;
        if (y < 0 || y >= Config.HEIGHT) return;
        cells[x][y].setColor(color);
    }

    private void setCellCondition(int x, int y, boolean arg) {
        if (x < 0 || x >= Config.WIDTH) return;
        if (y < 0 || y >= Config.HEIGHT) return;
        if (arg)
            cells[x][y].occupied();
        else
            cells[x][y].deOccupied();
    }

    @Override
    public boolean isCellOccupied(int x, int y) {
        if (x < 0 || x >= Config.WIDTH) return false;
        if (y < 0 || y >= Config.HEIGHT) return false;
        return cells[x][y].isOccupied();
    }

    public void moveFigure(int Ox, int Oy) {
        hideFigure();
        figure.moveFigure(Ox, Oy);
        showFigure();
    }

    public void turnFigure() {
        hideFigure();
        figure.turnFigure();
        showFigure();
    }

    private class KeyReader extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            super.keyPressed(e);

            switch (e.getKeyCode()) {
                case KeyEvent.VK_UP -> turnFigure();
                case KeyEvent.VK_DOWN -> moveFigure(0, 1);
                case KeyEvent.VK_LEFT -> moveFigure(-1, 0);
                case KeyEvent.VK_RIGHT -> moveFigure(1, 0);
                default -> moveFigure(0, -1);
            }
        }
    }

    private boolean isFullLine(int y) {
        for (int x = 0; x < Config.WIDTH; x++) {
            if (!isCellOccupied(x, y))
                return false;
        }
        return true;
    }

    private void destroyLines() {
        for (int y = Config.HEIGHT - 1; y >= 0; y--) {
            while (isFullLine(y)) {
                for (int cur_y = y - 1; cur_y >= 0; cur_y--) {
                    for (int x = 0; x < Config.WIDTH; x++) {
                        setCellColor(x, cur_y + 1, cells[x][cur_y].getColor());
                        setCellCondition(x, cur_y + 1, cells[x][cur_y].isOccupied());
                    }
                }
                for (int x = 0; x < Config.WIDTH; x++) {
                    setCellColor(x, 0, Config.GAME_COLOR);
                    setCellCondition(x, 0, false);
                }
                scores++;
                jTextField.setText("SCORES: " + scores.toString());
            }
        }
    }

    private class TimeAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            moveFigure(0, 1);

            if (figure.isLanded()) {
                for (Coord point : figure.getFigure().points) {
                    setCellCondition(figure.getCoord().x() + point.x(), figure.getCoord().y() + point.y(), true);
                }
                addFigure();
            }

            destroyLines();
        }
    }
}
