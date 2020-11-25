package eg.edu.alexu.csd.oop.draw.cs5.backendpaint.models;

import eg.edu.alexu.csd.oop.draw.cs5.backendpaint.models.shapes.Shape;

import java.util.LinkedList;

public class Board {
    LinkedList<Shape> shapes;

    public Board(Board board) {
        shapes = new LinkedList<>();
        shapes = board.shapes;
    }

    public void addShape (Shape shape) {
        shapes.add(shape);
    }



}
