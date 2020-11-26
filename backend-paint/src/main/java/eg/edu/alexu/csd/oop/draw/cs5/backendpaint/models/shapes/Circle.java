package eg.edu.alexu.csd.oop.draw.cs5.backendpaint.models.shapes;

import java.awt.*;
import java.util.List;

public class Circle extends Ellipse{
    double R;
    public void setR(double R){
        this.R = R;
    }

    public Circle(List<Point> vertices) {
        super(vertices);
        shapeType = ShapeType.CIRCLE;
        //vertices[0] = center
        //vertices[1] = point on circle


        setR(distBetTwoPoints(vertices.get(0),vertices.get(1)));
    }
    double distBetTwoPoints (Point a, Point b) {
        return Math.sqrt(Math.pow((a.x - b.x), 2) + Math.pow((a.y - b.y), 2));
    }
}
