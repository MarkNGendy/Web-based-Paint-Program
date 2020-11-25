package eg.edu.alexu.csd.oop.draw.cs5.backendpaint.models.shapes;

import java.awt.*;
import java.util.List;

public class Circle extends Ellipse{

    public Circle(List<Point> vertices) {
        super(vertices);
        shapeType = ShapeType.CIRCLE;
    }
}
