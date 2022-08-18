package model;

import java.util.ArrayList;
import java.util.List;

public enum Figure {
    I1(0, 1,    1, 1,    2, 1,    3, 1),//oooo
    I2(1, 0,
              1, 1,
              1, 2,
              1, 3),//oooo
    J,
    L,
    O,
    S,
    T,
    Z;

    private List<Coord> dot;

    Figure(int... coords) {
        dot = new ArrayList<Coord>();
        for (int i = 0; i < coords.length; i += 2) {
            dot.add(new Coord(coords[i], coords[i + 1]));
        }
    }
}
