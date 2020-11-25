package eg.edu.alexu.csd.oop.draw.cs5.backendpaint.models.shapes;

import java.awt.*;
import java.util.List;

public class Line extends Shape{


    public Line(List<Point> vertices) {
        points = vertices;
        this.shapeType = ShapeType.LINE;
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
