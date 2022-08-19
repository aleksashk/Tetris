package ui;

import model.Coord;
import model.Figures;
import service.FlyFigure;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Window extends JFrame implements Runnable {

    private Box[][] boxes;
    private FlyFigure fly;

    public Window() {
        boxes = new Box[Config.WIDTH][Config.HEIGHT];
        initForm();
        initBoxes();
        addKeyListener(new KeyAdapter());
    }

    public void addFigure() {
        fly = new FlyFigure();
        showFigure();
    }

    private void initForm() {
        setSize(Config.WIDTH * Config.SIZE + 15,
                Config.HEIGHT * Config.SIZE + 40);

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

    private void showFigure(Figures figure, Coord at, int color) {
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

    private class KeyAdapter implements KeyListener {
        @Override
        public void keyTyped(KeyEvent e) {
        }

        @Override
        public void keyPressed(KeyEvent e) {
            hideFigure();
            switch (e.getKeyCode()) {
                case KeyEvent.VK_LEFT:
                    moveFigure(-1, 0);
                    break;
                case KeyEvent.VK_RIGHT:
                    moveFigure(+1, 0);
                    break;
                case KeyEvent.VK_UP:
                    moveFigure(0, -1);
                    break;
                case KeyEvent.VK_DOWN:
                    moveFigure(0, +1);
                    break;
                case KeyEvent.VK_SPACE:
                    turnFigure();
                    break;
            }
            showFigure();
        }

        @Override
        public void keyReleased(KeyEvent e) {
        }
    }
}
