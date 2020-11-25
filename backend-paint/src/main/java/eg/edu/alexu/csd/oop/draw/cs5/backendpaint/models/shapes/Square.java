package eg.edu.alexu.csd.oop.draw.cs5.backendpaint.models.shapes;

import java.awt.*;
import java.util.List;

public class Square extends Rectangle{


    public Square(List<Point> vertices) {
        super(vertices);
        this.shapeType = ShapeType.SQUARE;
    }
}
