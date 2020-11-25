package eg.edu.alexu.csd.oop.draw.cs5.backendpaint.models.shapes;

import java.awt.*;
import java.util.LinkedList;

public class Square extends Rectangle{


    public Square(LinkedList<Point> vertices) {
        super(vertices);
        this.type = ShapeType.SQUARE;
    }
}
