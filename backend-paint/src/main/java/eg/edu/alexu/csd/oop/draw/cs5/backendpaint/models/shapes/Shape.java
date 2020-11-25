package eg.edu.alexu.csd.oop.draw.cs5.backendpaint.models.shapes;

import java.awt.*;
import java.util.LinkedList;

public abstract class Shape {
    LinkedList<Point> shapePoints;
    ShapeType type;
    int colour;

    public LinkedList<Point> getShapePoints() {
        return shapePoints;
    }


    public ShapeType getType() {
        return type;
    }

    abstract void move();
    abstract void resize();
    abstract void delete();
    abstract void colourShape();

}
