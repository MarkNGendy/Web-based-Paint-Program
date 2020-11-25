package eg.edu.alexu.csd.oop.draw.cs5.backendpaint.models.shapes;
import java.awt.Point;
import java.util.LinkedList;

public class Triangle extends Shape{


    public Triangle(LinkedList<Point> vertices) {
        shapePoints = vertices;
        this.type = ShapeType.TRIANGLE;
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
