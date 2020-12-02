package eg.edu.alexu.csd.oop.draw.cs5.backendpaint.paintapplication.models.shapes;

import java.util.List;
import eg.edu.alexu.csd.oop.draw.cs5.backendpaint.paintapplication.models.Point;

public class Square extends Rectangle {

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
        Point fourth = new Point();
        double maxYDistance = points.get(1).getY() - origin.getY();
        double maxXDistance = points.get(1).getX() - origin.getX();
        setSideLength(Math.max(Math.abs(maxXDistance), Math.abs(maxYDistance)));
        if (maxXDistance >= 0 && maxYDistance >= 0) {
            fourth.setX(origin.getX() + sideLength);
            fourth.setY(origin.getY() + sideLength);
            setWidth(sideLength);
            setLength(sideLength);
        } else if (maxXDistance < 0 && maxYDistance < 0) {
            fourth.setX(origin.getX() - sideLength);
            fourth.setY(origin.getY() - sideLength);
            setWidth(-sideLength);
            setLength(-sideLength);
        } else if (maxXDistance < 0 && maxYDistance >= 0) {
            fourth.setX(origin.getX() - sideLength);
            fourth.setY(origin.getY() + sideLength);
            setWidth(-sideLength);
            setLength(sideLength);
        } else if (maxXDistance >= 0 && maxYDistance < 0) {
            fourth.setX(origin.getX() + sideLength);
            fourth.setY(origin.getY() - sideLength);
            setWidth(sideLength);
            setLength(-sideLength);
        }
    }
}
