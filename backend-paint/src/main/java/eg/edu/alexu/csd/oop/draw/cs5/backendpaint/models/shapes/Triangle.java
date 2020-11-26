package eg.edu.alexu.csd.oop.draw.cs5.backendpaint.models.shapes;
import java.awt.Point;
import java.util.List;

public class Triangle extends Shape{

    public Triangle(List<Point> vertices) {
        super(vertices, ShapeType.TRIANGLE);
        points = vertices;
        this.shapeType = ShapeType.TRIANGLE;
    }

}
