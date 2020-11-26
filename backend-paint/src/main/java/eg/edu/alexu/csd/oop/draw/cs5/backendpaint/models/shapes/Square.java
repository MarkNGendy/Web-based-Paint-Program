package eg.edu.alexu.csd.oop.draw.cs5.backendpaint.models.shapes;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Square extends Rectangle{


    public Square(List<Point> vertices) {
        //vertices[0] = first point
        //vertices[1] = end point
        super(vertices);
        this.shapeType = ShapeType.SQUARE;
        points = new ArrayList<>();
        points.add(0,vertices.get(0));
        points.add(1,new Point(vertices.get(1).x,vertices.get(0).y));
        points.add(2,vertices.get(1));
        points.add(3,new Point(vertices.get(0).x,vertices.get(1).y));
    }
}
