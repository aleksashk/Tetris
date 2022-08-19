package service;

import model.Coord;
import model.Figures;
import ui.Config;

public class FlyFigure {
    private Figures figure;
    private Coord coord;

    public FlyFigure(Figures figure, Coord coord) {
        this.figure = Figures.getRandom();
        this.coord = new Coord(Config.WIDTH / 2 - 2, -figure.top.y);
    }

    private boolean canMoveFigure(Figures figure, int sx, int sy) {
        if (coord.x + sx + figure.top.x < 0) {
            return false;
        }
        if (coord.x + sx + figure.bot.x >= Config.WIDTH) {
            return false;
        }
//        if (coord.y + sy + figure.top.y < 0) {
//            return false;
//        }
        if (coord.y + sy + figure.bot.y >= Config.HEIGHT) {
            return false;
        }
        return true;
    }

    private void moveFigure(int sx, int sy) {
        if (canMoveFigure(figure, sx, sy)) {
            coord = coord.plus(sx, sy);
        }
    }

    private void turnFigure() {
        Figures rotated = figure.turnRight();
        if (canMoveFigure(rotated, 0, 0)) {
            figure = rotated;
        } else if (canMoveFigure(rotated, 1, 0)) {
            figure = rotated;
            moveFigure(1, 0);
        } else if (canMoveFigure(rotated, -1, 0)) {
            figure = rotated;
            moveFigure(-1, 0);
        } else if (canMoveFigure(rotated, 0, -1)) {
            figure = rotated;
            moveFigure(0, -1);
        }
    }
}
