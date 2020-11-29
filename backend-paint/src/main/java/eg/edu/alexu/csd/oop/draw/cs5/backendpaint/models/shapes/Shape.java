package eg.edu.alexu.csd.oop.draw.cs5.backendpaint.models.shapes;

import java.awt.*;
import java.util.List;

public abstract class Shape {

    List<Point> points;
    ShapeType shapeType;
    String colour;
    int indexInBoard;
    String stroke;
    String strokeWidth;


    public void setStroke(String stroke) {
        this.stroke = stroke;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    public void setStrokeWidth(String strokeWidth) {
        this.strokeWidth = strokeWidth;
    }

    public Shape(List<Point> points, ShapeType shapeType) {
        this.points = points;
        this.shapeType = shapeType;
    }



    public ShapeType getShapeType() {
        return shapeType;
    }

    public List<Point> getPoints() {
        return points;
    }

    public String getColour() {
        return colour;
    }

    public int getIndexInBoard() {
        return indexInBoard;
    }

    public String getStroke() {
        return stroke;
    }

    public String getStrokeWidth() {
        return strokeWidth;
    }

    public void setIndexInBoard(int indexInBoard) {
        this.indexInBoard = indexInBoard;
    }
}
