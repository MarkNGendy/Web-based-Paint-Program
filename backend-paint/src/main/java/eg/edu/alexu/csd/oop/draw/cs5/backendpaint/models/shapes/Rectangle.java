package eg.edu.alexu.csd.oop.draw.cs5.backendpaint.models.shapes;

import java.util.List;
import eg.edu.alexu.csd.oop.draw.cs5.backendpaint.models.Point;


public class Rectangle extends Shape{

    public double getLength() {
        return length;
    }

    public double getWidth() {
        return width;
    }

    double length;
    double width;


    public void setLength(Double length) {
        this.length = length;
    }

    public void setWidth(Double width) {
        this.width = width;
    }

    public Rectangle(List<Point> points, ShapeType shapeType) {
        super(points, shapeType);
        Point origin = points.get(0);
        Point second = new Point(points.get(0).getX(), points.get(1).getY());
        Point third = new Point(points.get(1).getX(), points.get(0).getY());
        setWidth(third.getX() - origin.getX());
        setLength(second.getY() - origin.getY());
    }
}
