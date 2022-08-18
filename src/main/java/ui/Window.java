package ui;

import model.Coord;
import model.Figure;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Window extends JFrame implements Runnable {

    private Box[][] boxes;
    private Figure figure;
    private Coord coord;

    public Window() {
        boxes = new Box[Config.WIDTH][Config.HEIGHT];
        initForm();
        initBoxes();
        addKeyListener(new KeyAdapter());
    }

    public void addFigure() {
        figure = Figure.getRandom();
        coord = new Coord(5, 5);
        showFigure();
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
        repaint();
    }

    private void showFigure() {
        showFigure(figure, coord, 1);
    }

    private void hideFigure() {
        showFigure(figure, coord, 0);
    }

    private void showFigure(Figure figure, Coord at, int color) {
        for (Coord dot : figure.dots) {
            setBoxColor(at.x + dot.x, at.y + dot.y, color);
        }
    }

    private void setBoxColor(int x, int y, int color) {
        if (x < 0 || x >= Config.WIDTH) {
            return;
        }
        if (y < 0 || y >= Config.HEIGHT) {
            return;
        }
        boxes[x][y].setColor(color);
    }

    class KeyAdapter implements KeyListener {
        public void keyTyped(KeyEvent e) {
        }

        public void keyPressed(KeyEvent e) {
            hideFigure();
            switch (e.getKeyCode()) {
                case KeyEvent.VK_LEFT:
                    coord = new Coord(coord.x - 1, coord.y);
                    break;
                case KeyEvent.VK_RIGHT:
                    coord = new Coord(coord.x + 1, coord.y);
                    break;
            }
            showFigure();
        }

        public void keyReleased(KeyEvent e) {
        }
    }
}
