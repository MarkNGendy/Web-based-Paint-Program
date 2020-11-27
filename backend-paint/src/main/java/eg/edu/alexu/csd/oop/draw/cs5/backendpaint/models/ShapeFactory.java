package eg.edu.alexu.csd.oop.draw.cs5.backendpaint.models;

import eg.edu.alexu.csd.oop.draw.cs5.backendpaint.models.shapes.*;
import eg.edu.alexu.csd.oop.draw.cs5.backendpaint.models.shapes.Rectangle;
import eg.edu.alexu.csd.oop.draw.cs5.backendpaint.models.shapes.Shape;
import java.awt.*;
import java.util.List;

public class ShapeFactory {
    public enum ShapeType {
        SQUARE, RECTANGLE, TRIANGLE, ELLIPSE, CIRCLE, LINE;
    }

    private static ShapeFactory shapeFactory;

    private ShapeFactory() {

    }

    public static ShapeFactory getShapeFactory() {
        if (shapeFactory == null) {
            shapeFactory = new ShapeFactory();
        }
        return shapeFactory;
    }

    public Shape createShape(ShapeType shapeType, List<Point> vertices) {
        switch (shapeType) {
            case LINE:
                return new Line(vertices);
            case CIRCLE:
                return new Circle(vertices);
            case SQUARE:
                return new Square(vertices);
            case ELLIPSE:
                return new Ellipse(vertices);
            case TRIANGLE:
                return new Triangle(vertices);
            case RECTANGLE:
                return new Rectangle(vertices);
        }
        return null;
    }
}
