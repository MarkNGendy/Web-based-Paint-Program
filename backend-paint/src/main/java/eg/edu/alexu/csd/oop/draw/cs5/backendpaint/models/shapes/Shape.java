package eg.edu.alexu.csd.oop.draw.cs5.backendpaint.models.shapes;

import java.awt.*;
import java.util.List;

public abstract class Shape {
    List<Point> points;
    ShapeType shapeType;

    public Shape(List<Point> points, ShapeType shapeType) {
        this.points = points;
        this.shapeType = shapeType;
    }


    public List<Point> getPoints() {
        return points;
    }


    public ShapeType getShapeType() {
        return shapeType;
    }

    abstract void delete();
    abstract void colourShape();
}
