package eg.edu.alexu.csd.oop.draw.cs5.backendpaint.controllers;

import eg.edu.alexu.csd.oop.draw.cs5.backendpaint.models.shapes.ShapeType;
import eg.edu.alexu.csd.oop.draw.cs5.backendpaint.models.Point;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "ShapeDTO")
@XmlAccessorType(XmlAccessType.FIELD)
public class ShapeDTO {

    @XmlElement(name = "points")
    private List<Point> points;
    @XmlElement(name = "shapeType")
    private ShapeType shapeType;
    @XmlElement(name = "colour")
    private String colour;
    @XmlElement(name = "indexInBoard")
    private int indexInBoard;
    @XmlElement(name = "stroke")
    private String stroke;
    @XmlElement(name = "strokeWidth")
    private String strokeWidth;
    @XmlElement(name = "hRadius")
    private double hRadius;
    @XmlElement(name = "vRadius")
    private double vRadius;
    @XmlElement(name = "radius")
    private double radius;

    public ShapeDTO() {
    }

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
