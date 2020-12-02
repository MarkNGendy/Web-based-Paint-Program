package eg.edu.alexu.csd.oop.draw.cs5.backendpaint.paintapplication.controllers;

import eg.edu.alexu.csd.oop.draw.cs5.backendpaint.paintapplication.models.shapes.ShapeType;
import eg.edu.alexu.csd.oop.draw.cs5.backendpaint.paintapplication.models.Point;
import java.util.List;

public class ShapeDTO {

    private List<Point> points;
    private ShapeType shapeType;
    private String colour;
    private int indexInBoard;
    private String stroke;
    private String strokeWidth;
    private double hRadius;
    private double vRadius;
    private double radius;

    public double gethRadius() {
        return hRadius;
    }

    public double getvRadius() {
        return vRadius;
    }

    public double getRadius() {
        return radius;
    }

    public double getLength() {
        return length;
    }

    public double getWidth() {
        return width;
    }

    public double getSideLength() {
        return sideLength;
    }

    public void sethRadius(double hRadius) {
        this.hRadius = hRadius;
    }

    public void setvRadius(double vRadius) {
        this.vRadius = vRadius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    public void setLength(double length) {
        this.length = length;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public void setSideLength(double sideLength) {
        this.sideLength = sideLength;
    }

    double length;
    double width;
    double sideLength;

    public ShapeDTO(List<Point> points, ShapeType shapeType, String colour, int indexInBoard, String stroke,
            String strokeWidth) {
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
