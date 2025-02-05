package eg.edu.alexu.csd.oop.draw.cs5.backendpaint.models;

import eg.edu.alexu.csd.oop.draw.cs5.backendpaint.models.shapes.*;
import eg.edu.alexu.csd.oop.draw.cs5.backendpaint.models.shapes.Rectangle;
import eg.edu.alexu.csd.oop.draw.cs5.backendpaint.models.shapes.Shape;

import java.util.List;

public class ShapeFactory {

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
                return new Line(vertices, shapeType);
            case CIRCLE:
                return new Circle(vertices, shapeType);
            case SQUARE:
                return new Square(vertices, shapeType);
            case ELLIPSE:
                return new Ellipse(vertices, shapeType);
            case TRIANGLE:
                return new Triangle(vertices, shapeType);
            case RECTANGLE:
                return new Rectangle(vertices, shapeType);
        }
        return null;
    }
}
