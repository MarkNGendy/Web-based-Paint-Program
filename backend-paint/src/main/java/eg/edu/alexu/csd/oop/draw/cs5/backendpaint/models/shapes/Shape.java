package eg.edu.alexu.csd.oop.draw.cs5.backendpaint.models.shapes;

import java.awt.*;

public abstract class Shape {
    Point[] shapePoints;

    abstract void move();
    abstract void resize();
    abstract void delete();
    abstract void colourShape();

}
