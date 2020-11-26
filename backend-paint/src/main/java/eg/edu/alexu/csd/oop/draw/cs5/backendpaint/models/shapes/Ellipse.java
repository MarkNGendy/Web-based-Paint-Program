package eg.edu.alexu.csd.oop.draw.cs5.backendpaint.models.shapes;

import java.awt.*;
import java.util.List;

public class Ellipse extends Shape{

    double vRadius;
    double hRadius;

    public Ellipse(List<Point> vertices) {
        super(vertices, ShapeType.ELLIPSE);
        points = vertices;
        //shapePoints[0] Center
        //shapePoints[1] vertical
        // shapePoints[2] horizontal
        setvRadius(distBetTwoPoints(points.get(0), points.get(1)));
        sethRadius(distBetTwoPoints(points.get(0), points.get(2)));
        this.shapeType = ShapeType.ELLIPSE;
    }

    public void setvRadius(double vRadius) {
        this.vRadius = vRadius;
    }

    public void sethRadius(double hRadius) {
        this.hRadius = hRadius;
    }


    double distBetTwoPoints (Point a, Point b) {
        return Math.sqrt(Math.pow((a.x - b.x), 2) + Math.pow((a.y - b.y), 2));
    }
}
