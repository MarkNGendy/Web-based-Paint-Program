package eg.edu.alexu.csd.oop.draw.cs5.backendpaint.models;

import eg.edu.alexu.csd.oop.draw.cs5.backendpaint.models.shapes.*;

public class ShapeFactory {
    public Shape getShapeI(ShapeType shapeType) {
        return switch (shapeType) {
            case LINE -> new Line();
            case CIRCLE -> new Circle();
            case SQUARE -> new Square();
            case ELLIPSE -> new Ellipse();
            case TRIANGLE -> new Triangle();
            case RECTANGLE -> new Rectangle();
        };
    }
}
