package eg.edu.alexu.csd.oop.draw.cs5.backendpaint.models.shapes;

import java.util.List;
import eg.edu.alexu.csd.oop.draw.cs5.backendpaint.models.Point;

public class Ellipse extends Shape{

    public double gethRadius() {
        return hRadius;
    }

    public double getvRadius() {
        return vRadius;
    }

    double hRadius;
    double vRadius;

    public void sethRadius(Double hRadius) {
        this.hRadius = hRadius;
    }

    public void setvRadius(Double vRadius) {
        this.vRadius = vRadius;
    }

    public Ellipse(List<Point> points, ShapeType shapeType) {
        super(points, shapeType);
        sethRadius(Shape.distBetTwoPoints(points.get(0), points.get(1)));
        setvRadius(Shape.distBetTwoPoints(points.get(0), points.get(2)));
    }

}
