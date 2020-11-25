package eg.edu.alexu.csd.oop.draw.cs5.backendpaint.models.shapes;

import java.awt.*;
import java.util.LinkedList;

public class Rectangle extends Shape{

    double length;
    double width;

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public Rectangle(LinkedList<Point> vertices) {
        shapePoints = new LinkedList<>();
        shapePoints.add(0, vertices.get(0));
        shapePoints.add(1, new Point(vertices.get(0).x, vertices.get(1).y));
        shapePoints.add(2, new Point(vertices.get(1).x, vertices.get(0).y));
        shapePoints.add(3, vertices.get(1));
        type = ShapeType.RECTANGLE;
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
}
