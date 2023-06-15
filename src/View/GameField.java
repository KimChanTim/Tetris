package View;

import javax.swing.*;

public class GameField extends JPanel {
    private final Cell[][] cells;
    private JTextField jTextField;

    public GameField(Cell[][] cells) {
        this.cells = cells;
        setBounds(0, 0, Config.WIDTH * Config.cellSIZE, (Config.HEIGHT + 1) * Config.cellSIZE);

        initCells();

        jTextField = new JTextField();
        add(jTextField);
        jTextField.setBounds(0, Config.HEIGHT * Config.cellSIZE, Config.WIDTH * Config.cellSIZE, Config.cellSIZE);
        jTextField.setVisible(false);

        setFocusable(false);
        requestFocusInWindow();

        setLayout(null);
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

    public void showCellsScores() {
        for (int x = 0; x < Config.WIDTH; x++) {
            for (int y = 0; y < Config.HEIGHT; y++) {
                cells[x][y].setColor(Config.GAME_COLOR);
                cells[x][y].setVisible(true);
            }
        }

        jTextField.setVisible(true);
        jTextField.setText("SCORES: 0");
    }
    public void hideCellsScores() {
        for (int x = 0; x < Config.WIDTH; x++) {
            for (int y = 0; y < Config.HEIGHT; y++) {
                cells[x][y].setVisible(false);
                cells[x][y].deOccupied();
            }
        }

        jTextField.setVisible(false);
    }
    public void setjTextField(Integer scores) {
        jTextField.setText("SCORES: " + scores.toString());
    }

}
