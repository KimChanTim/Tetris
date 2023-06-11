package Mode;

import java.util.ArrayList;
import java.util.List;

public enum Figure {
    O_FORM(0, 0, 1, 0, 0, 1, 1, 1),
    I1_FORM(0, 1, 1, 1, 2, 1, 3, 1),
    I2_FORM(1, 0, 1, 1, 1, 2, 1, 3),
    J1_FORM(1, 0, 1, 1, 1, 2, 0, 2),
    J2_FORM(0, 0, 0, 1, 1, 1, 2, 1),
    J3_FORM(1, 0, 2, 0, 1, 1, 1, 2),
    J4_FORM(0, 1, 1, 1, 2, 1, 2, 2),
    L1_FORM(1, 0, 1, 1, 1, 2, 2, 2),
    L2_FORM(0, 1, 1, 1, 2, 1, 0, 2),
    L3_FORM(0, 0, 1, 0, 1, 1, 1, 2),
    L4_FORM(2, 0, 0, 1, 1, 1, 2, 1),
    T1_FORM(0, 1, 1, 1, 2, 1, 1, 2),
    T2_FORM(0, 1, 1, 0, 1, 1, 1, 2),
    T3_FORM(1, 0, 0, 1, 1, 1, 2, 1),
    T4_FORM(1, 0, 1, 1, 2, 1, 1, 2),
    S1_FORM(2, 1, 1, 1, 0, 2, 1, 2),
    S2_FORM(0, 0, 0, 1, 1, 1, 1, 2),
    Z1_FORM(0, 1, 1, 1, 1, 2, 2, 2),
    Z2_FORM(2, 0, 1, 1, 2, 1, 1, 2);

    public final List<Coord> points;
    public final Coord leftCorner;
    public final Coord rightCorner;

    private Figure(int ... coords) {
        points = new ArrayList<>();
        for (int i = 0; i < coords.length; i += 2) {
            points.add(new Coord(coords[i], coords[i + 1]));
        }
        leftCorner = setLeftCorner();
        rightCorner = setRightCorner();
    }

    private Coord setLeftCorner() {
        int x = points.get(0).x();
        int y = points.get(0).y();

        for (Coord point : points) {
            if (point.x() < x)
                x = point.x();
            if (point.y() < y)
                y = point.y();
        }
        return new Coord(x, y);
    }

    private Coord setRightCorner() {
        int x = points.get(0).x();
        int y = points.get(0).y();

        for (Coord point : points) {
            if (point.x() > x)
                x = point.x();
            if (point.y() > y)
                y = point.y();
        }
        return new Coord(x, y);
    }

    public Figure turn() {
        return switch (this) {
            case O_FORM -> O_FORM;
            case I1_FORM -> I2_FORM;
            case I2_FORM -> I1_FORM;
            case J1_FORM -> J2_FORM;
            case J2_FORM -> J3_FORM;
            case J3_FORM -> J4_FORM;
            case J4_FORM -> J1_FORM;
            case T1_FORM -> T2_FORM;
            case T2_FORM -> T3_FORM;
            case T3_FORM -> T4_FORM;
            case T4_FORM -> T1_FORM;
            case L1_FORM -> L2_FORM;
            case L2_FORM -> L3_FORM;
            case L3_FORM -> L4_FORM;
            case L4_FORM -> L1_FORM;
            case S1_FORM -> S2_FORM;
            case S2_FORM -> S1_FORM;
            case Z1_FORM -> Z2_FORM;
            case Z2_FORM -> Z1_FORM;
        };
    }

    public static Figure getRandom() {
        return switch ((int)(Math.random() * 7)) {
            case 0 -> O_FORM;
            case 1 -> I1_FORM;
            case 2 -> J1_FORM;
            case 3 -> L1_FORM;
            case 4 -> T1_FORM;
            case 5 -> S1_FORM;
            case 6 -> Z1_FORM;
            default -> O_FORM;
        };
    }
}
