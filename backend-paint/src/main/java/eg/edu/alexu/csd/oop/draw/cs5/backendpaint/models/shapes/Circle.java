package eg.edu.alexu.csd.oop.draw.cs5.backendpaint.models.shapes;

import java.awt.*;
import java.util.LinkedList;

public class Circle extends Ellipse{

    public Circle(LinkedList<Point> vertices) {
        super(vertices);
        type = ShapeType.CIRCLE;
    }
}
