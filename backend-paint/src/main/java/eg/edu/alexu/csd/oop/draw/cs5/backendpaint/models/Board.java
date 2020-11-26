package eg.edu.alexu.csd.oop.draw.cs5.backendpaint.models;

import eg.edu.alexu.csd.oop.draw.cs5.backendpaint.models.shapes.Shape;

import java.util.ArrayList;
import java.util.List;

public class Board {
    List<Shape> shapes;

    public Board(Board board) {
        shapes = new ArrayList<>();
        shapes = board.shapes;
    }

    public void addShape (Shape shape) {
        shapes.add(shape);
    }



}
