package Mode;

import View.CellsState;
import View.Config;

import java.awt.*;

public class CurentFigure {
    private final CellsState state;
    private Figure figure;
    private Coord coord;
    private boolean landed;
    private int ticks;
    private Color color;

    public CurentFigure(CellsState cellsState) {
        figure = Figure.getRandom();
        coord = new Coord(Config.WIDTH / 2 - 1, -figure.leftCorner.y());
        state = cellsState;
        ticks = 2;
        landed = false;
        setColor();
    }

    private void setColor() {
        switch (figure) {
            case O_FORM -> color = Color.CYAN;
            case I1_FORM -> color = Color.RED;
            case J1_FORM -> color = Color.YELLOW;
            case L1_FORM -> color = Color.GREEN;
            case T1_FORM -> color = Color.BLUE;
            case S1_FORM -> color = Color.ORANGE;
            case Z1_FORM -> color = Color.PINK;
        }
    }

    public Figure getFigure() {
        return figure;
    }

    public Coord getCoord() {
        return coord;
    }

    public boolean isLanded() {
        return landed;
    }
    public Color getColor() {
        return color;
    }

    public boolean canMoveFigure(Figure new_figure, int Ox, int Oy) {
        if (coord.x() + Ox + new_figure.leftCorner.x() < 0) return false;
        if (coord.x() + Ox + new_figure.rightCorner.x() >= Config.WIDTH) return false;
        if (coord.y() + Oy + new_figure.rightCorner.y() >= Config.HEIGHT) return false;
        if (coord.y() + Oy + new_figure.leftCorner.y() < 0) return false;

        for (Coord point : new_figure.points) {
            if (state.isCellOccupied(point.x() + coord.x() + Ox, point.y() + coord.y() + Oy)) {
                return false;
            }
        }
        return true;
    }

    public boolean canPlaceFigure() {
        return canMoveFigure(figure, 0, 0);
    }

    public void moveFigure(int Ox, int Oy) {
        if (canMoveFigure(figure, Ox, Oy)) {
            coord = new Coord(coord.x() + Ox, coord.y() + Oy);
            if (Oy == 1)
                ticks = 2;

        } else if (Oy == 1) {
            if (ticks > 0)
                ticks--;
            else
                landed = true;
        }
    }

    public void turnFigure() {
        Figure new_figure = figure.turn();
        if (canMoveFigure(new_figure, 0, 0)) {
            figure = new_figure;
        } else if (canMoveFigure(new_figure, 1, 0)) {
            figure = new_figure;
            moveFigure(1, 0);
        } else if (canMoveFigure(new_figure, -1, 0)) {
            figure = new_figure;
            moveFigure(-1, 0);
        }
    }

}
