package eg.edu.alexu.csd.oop.draw.cs5.backendpaint.models.shapes;

import java.awt.*;
import java.util.List;

public class Ellipse extends Shape{

    double vDiameter;
    double hDiameter;

    public Ellipse(List<Point> vertices) {
        super(vertices, ShapeType.ELLIPSE);
        points = vertices;
        //shapePoints[0] Center
        //shapePoints[1] vertical
        // shapePoints[2] horizontal
        setvDiameter(distBetTwoPoints(points.get(0), points.get(1)));
        sethDiameter(distBetTwoPoints(points.get(0), points.get(2)));
        this.shapeType = ShapeType.ELLIPSE;
    }

    public void setvDiameter(double vDiameter) {
        this.vDiameter = vDiameter;
    }

    public void sethDiameter(double hDiameter) {
        this.hDiameter = hDiameter;
    }

    @Override
    void move() {

    }

    @Override
    void resize() {

    }

    @Override
    void delete() {

    }

    @Override
    void colourShape() {

    }

    double distBetTwoPoints (Point a, Point b) {
        return Math.sqrt(Math.pow((a.x - b.x), 2) + Math.pow((a.y - b.y), 2));
    }
}
