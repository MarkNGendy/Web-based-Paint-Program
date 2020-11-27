package eg.edu.alexu.csd.oop.draw.cs5.backendpaint.models.shapes;

import java.awt.*;
import java.util.List;

public abstract class Shape {

    int indexInBoard;
    List<Point> points;
    ShapeType shapeType;

    protected Shape(List<Point> points, ShapeType shapeType, int index) {
        this.points = points;
        this.shapeType = shapeType;
        indexInBoard = index;
    }

    public List<Point> getPoints() {
        return points;
    }

    public ShapeType getShapeType() {
        return shapeType;
    }

    public int getIndexInBoard() {
        return indexInBoard;
    }

    // public void setIndexInBoard(int indexInBoard) {
    // this.indexInBoard = indexInBoard;
    // }
}
