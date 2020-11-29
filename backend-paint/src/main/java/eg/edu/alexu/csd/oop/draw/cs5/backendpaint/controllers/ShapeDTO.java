package eg.edu.alexu.csd.oop.draw.cs5.backendpaint.controllers;

import eg.edu.alexu.csd.oop.draw.cs5.backendpaint.models.shapes.ShapeType;

import java.awt.*;
import java.util.List;

public class ShapeDTO {

    List<Point> points;
    ShapeType shapeType;
    String colour;
    int indexInBoard;
    String stroke;
    String strokeWidth;

    public ShapeDTO(List<Point> points, ShapeType shapeType, String colour, int indexInBoard, String stroke, String strokeWidth) {
        this.points = points;
        this.shapeType = shapeType;
        this.colour = colour;
        this.indexInBoard = indexInBoard;
        this.stroke = stroke;
        this.strokeWidth = strokeWidth;
    }

    public List<Point> getPoints() {
        return points;
    }

    public ShapeType getShapeType() {
        return shapeType;
    }

    public String getColour() {
        return colour;
    }

    public String getStroke() {
        return stroke;
    }

    public String getStrokeWidth() {
        return strokeWidth;
    }

    public int getIndexInBoard() {
        return indexInBoard;
    }
}
