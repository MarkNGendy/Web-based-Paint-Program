package eg.edu.alexu.csd.oop.draw.cs5.backendpaint.models.shapes;

import java.util.ArrayList;
import java.util.List;
import eg.edu.alexu.csd.oop.draw.cs5.backendpaint.models.Point;
import eg.edu.alexu.csd.oop.draw.cs5.backendpaint.models.ShapeFactory;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "boardsList")
@XmlAccessorType(XmlAccessType.FIELD)
public abstract class Shape {

    @XmlElement(name = "points")
    List<Point> points;
    @XmlElement(name = "shapeType")
    ShapeType shapeType;
    @XmlElement(name = "colour")
    String colour;
    @XmlElement(name = "indexInBoard")
    int indexInBoard;
    @XmlElement(name = "stroke")
    String stroke;
    @XmlElement(name = "strokeWidth")
    String strokeWidth;

    double ratio = 1.0;

    public double getRatio() {
        return ratio;
    }

    public void setRatio(double ratio) {
        this.ratio = ratio;
    }

    public void setStroke(String stroke) {
        this.stroke = stroke;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    public void setStrokeWidth(String strokeWidth) {
        this.strokeWidth = strokeWidth;
    }

    public Shape() {
    }

    public void setPoints(List<Point> points) {
        this.points = points;
    }

    public void setShapeType(ShapeType shapeType) {
        this.shapeType = shapeType;
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

    public static double distBetTwoPoints(Point a, Point b) {
        double result = Math.sqrt(Math.abs(Math.pow((a.getX() - b.getX()), 2) + Math.pow((a.getY() - b.getY()), 2)));
        return result;
    }

    public void setIndexInBoard(int indexInBoard) {
        this.indexInBoard = indexInBoard;
    }

    public Shape deepCopy(Shape shape) {
        ShapeFactory shapeFactory = ShapeFactory.getShapeFactory();
        List<Point> pointList = new ArrayList<>();
        for (Point p : shape.getPoints()) {
            Point s = new Point();
            s.setX(p.getX());
            s.setY(p.getY());
            pointList.add(s);
        }
        System.out.println(pointList.size());
        Shape out = shapeFactory.createShape(this.shapeType, pointList);
        out.setColour(shape.getColour());
        out.setStrokeWidth(shape.getStrokeWidth());
        out.setStroke(shape.stroke);
        out.setIndexInBoard(shape.getIndexInBoard());
        System.out.println("resize" + shape.getRatio());
        out.resize(shape.getRatio());
        return out;
    }

    public abstract void resize(Double ratio);
}
