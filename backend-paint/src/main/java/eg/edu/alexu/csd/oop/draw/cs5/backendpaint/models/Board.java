package eg.edu.alexu.csd.oop.draw.cs5.backendpaint.models;

import eg.edu.alexu.csd.oop.draw.cs5.backendpaint.models.shapes.Shape;

import java.util.ArrayList;
import java.util.List;

public class Board {

    private static Board board;
    List<Shape> shapes;


    public void setShapes(List<Shape> shapes) {
        this.shapes = shapes;
    }

    private Board() {
        shapes = new ArrayList<>();
    }

    public static Board getBoard() {
        if (board == null) {
            board = new Board();
        }
        return board;
    }

    public void addShape (Shape shape) {
        shapes.add(shape);
        shape.setIndexInBoard(shapes.size() - 1);
    }

}
