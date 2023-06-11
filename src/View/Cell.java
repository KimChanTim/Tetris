package View;

import javax.swing.*;
import java.awt.*;

public class Cell extends JPanel {
    private boolean condition;
    private Color color;
    public Cell(int x, int y) {
        setBounds(x * Config.cellSIZE, y * Config.cellSIZE,
                Config.cellSIZE, Config.cellSIZE);
        condition = false;
    }

    public boolean isOccupied() {
        return condition;
    }
    public void occupied() {
        condition = true;
    }
    public void deOccupied() {
        condition = false;
    }
    public void setColor(Color color) {
        setBackground(color);
        this.color = color;
    }

    public Color getColor() {
        return color;
    }
}

