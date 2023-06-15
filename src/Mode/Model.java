package Mode;

import View.*;
import View.Window;
import View.Menu;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Model implements CellsState {
    private final Window window;
    private final Menu menu;
    private final GameField gameField;
    private final Cell[][] cells;
    private final Timer timer;
    private CurentFigure figure;
    private Integer scores;

    public Model() {
        cells = new Cell[Config.WIDTH][Config.HEIGHT];
        window = new Window();

        timer = new Timer(200, new TimeAction());

        menu = new Menu();
        gameField = new GameField(cells);
        window.add(menu);
        window.add(gameField);
        javax.swing.SwingUtilities.invokeLater(window);
    }

    public GameField getGameField() {
        return gameField;
    }
    public Menu getMenu() { return menu; }
    public Window getWindow() { return window; }

    public void startGame() {
        gameField.showCellsScores();
        timer.start();
        gameField.setFocusable(true);
        gameField.requestFocusInWindow();
        addFigure();
        scores = 0;
    }

    private void addFigure() {
        figure = new CurentFigure(this);
        if (!figure.canPlaceFigure()) {
            timer.stop();
            gameField.setFocusable(false);
            gameField.requestFocusInWindow();
            gameField.hideCellsScores();
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
                gameField.setjTextField(scores);
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
