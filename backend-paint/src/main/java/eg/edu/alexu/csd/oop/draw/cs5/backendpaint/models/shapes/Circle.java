package eg.edu.alexu.csd.oop.draw.cs5.backendpaint.models.shapes;

import java.util.List;

import eg.edu.alexu.csd.oop.draw.cs5.backendpaint.models.Point;


public class Circle extends Ellipse{

    public double getRadius() {
        return radius;
    }

    double radius;

    public void setRadius(double radius) {
        this.radius = radius;
    }

    public Circle(List<Point> points, ShapeType shapeType) {
        super(points, shapeType);
        setRadius(Shape.distBetTwoPoints(points.get(0), points.get(1)));
        setvRadius(0.0);
        sethRadius(0.0);
    }


}
