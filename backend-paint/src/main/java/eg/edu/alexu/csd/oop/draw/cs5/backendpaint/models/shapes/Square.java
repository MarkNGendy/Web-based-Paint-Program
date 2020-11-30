package eg.edu.alexu.csd.oop.draw.cs5.backendpaint.models.shapes;

import java.util.List;
import eg.edu.alexu.csd.oop.draw.cs5.backendpaint.models.Point;

public class Square extends Rectangle{

    double sideLength;

    public void setSideLength(double sideLength) {
        this.sideLength = sideLength;
    }

    public double getSideLength() {
        return sideLength;
    }

    public Square(List<Point> points, ShapeType shapeType) {
        super(points, shapeType);
        Point origin = points.get(0);
        Point second = null;
        if (Math.abs(points.get(1).getX()) >  Math.abs(points.get(1).getY())
                && (points.get(1).getX() < 0)) {
            second = new Point(points.get(1).getX(), points.get(1).getX());
        }
        else if (Math.abs(points.get(1).getX()) <  Math.abs(points.get(1).getY())
                && (points.get(1).getY() < 0)) {
            second = new Point(points.get(1).getY(), points.get(1).getY());
        } else {
            double maxPoint;
            maxPoint = Math.max(points.get(1).getX(), points.get(1).getY());
            if (points.get(1).getX() < 0 && points.get(1).getY() < 0) {
                second = new Point(-maxPoint, -maxPoint);
            }
            else if (points.get(1).getX() < 0) {
                second = new Point(-maxPoint, maxPoint);
            }
            else if (points.get(1).getY() < 0) {
                second = new Point(maxPoint, -maxPoint);
            } else {
                second = new Point(maxPoint, maxPoint);
            }
        }
        Point third = new Point(origin.getX(), second.getY());
        points.set(1, second);
        setSideLength(Shape.distBetTwoPoints(origin, third));
        setLength(0.0);
        setWidth(0.0);
    }
}
