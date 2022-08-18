package ui;

import model.Coord;
import model.Figure;

import javax.swing.*;

public class Window extends JFrame implements Runnable {

    Box[][] boxes;

    public Window() {
        boxes = new Box[Config.WIDTH][Config.HEIGHT];
    }

    private void initForm() {
        setSize(Config.WIDTH * Config.SIZE + 15,
                Config.HEIGHT * Config.SIZE + 30);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setTitle("Tetris Game");
        setLayout(null);
        setVisible(true);
    }

    private void initBoxes() {
        for (int x = 0; x < Config.WIDTH; x++) {
            for (int y = 0; y < Config.HEIGHT; y++) {
                boxes[x][y] = new Box(x, y);
                add(boxes[x][y]);
            }
        }
    }

    public void run() {
        initForm();
        initBoxes();
    }

    public void showFigure(Figure figure, Coord at) {
        for (Coord dot : figure.dots) {
            setBoxColor(at.x + dot.x, at.y + dot.y, 1);
        }
    }

    void setBoxColor(int x, int y, int color) {
        if (x < 0 || x >= Config.WIDTH) {
            return;
        }
        if (y < 0 || y >= Config.HEIGHT) {
            return;
        }
        boxes[x][y].setColor(color);
    }
}
