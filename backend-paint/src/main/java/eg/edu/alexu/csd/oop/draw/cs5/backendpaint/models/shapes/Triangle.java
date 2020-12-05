package eg.edu.alexu.csd.oop.draw.cs5.backendpaint.models.shapes;

import java.util.List;
import eg.edu.alexu.csd.oop.draw.cs5.backendpaint.models.Point;

public class Triangle extends Shape {

    public Triangle(List<Point> points, ShapeType shapeType) {
        super(points, shapeType);
    }

    @Override
    public void resize(Double ratio) {
        //// sfdag
        this.ratio = ratio;
    }
}
