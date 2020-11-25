package eg.edu.alexu.csd.oop.draw.cs5.backendpaint.models.shapes;

import java.awt.*;
import java.util.LinkedList;

public class Ellipse extends Shape{

    double vDiameter;
    double hDiameter;

    public Ellipse(LinkedList<Point> vertices) {
        shapePoints = vertices;
        //shapePoints[0] Center
        //shapePoints[1] vertical
        // shapePoints[2] horizontal
        setvDiameter(distBetTwoPoints(shapePoints.get(0), shapePoints.get(1)));
        sethDiameter(distBetTwoPoints(shapePoints.get(0), shapePoints.get(2)));
        this.type = ShapeType.ELLIPSE;
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
