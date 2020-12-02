package eg.edu.alexu.csd.oop.draw.cs5.backendpaint.paintapplication.models;

import eg.edu.alexu.csd.oop.draw.cs5.backendpaint.paintapplication.controllers.ShapeDTO;
import eg.edu.alexu.csd.oop.draw.cs5.backendpaint.paintapplication.models.shapes.Shape;

import java.util.ArrayList;
import java.util.List;

public class Board {

    List<Shape> shapes;

    public Board() {
        shapes = new ArrayList<>();
    }

    public List<Shape> getShapes() {
        return shapes;
    }

    public void setShapes(List<Shape> shapes) {
        this.shapes = new ArrayList<>();
        this.shapes.addAll(shapes);
    }

    public void addShape(Shape shape) {
        shape.setIndexInBoard(shapes.size());
        shapes.add(shape);
    }

}
