package eg.edu.alexu.csd.oop.draw.cs5.backendpaint.models.shapes;

import java.awt.*;
import java.util.LinkedList;

public class Line extends Shape{


    public Line(LinkedList<Point> vertices) {
        shapePoints = vertices;
        this.type = ShapeType.LINE;
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
