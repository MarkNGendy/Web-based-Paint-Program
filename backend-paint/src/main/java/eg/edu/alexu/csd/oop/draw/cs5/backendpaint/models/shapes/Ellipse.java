package eg.edu.alexu.csd.oop.draw.cs5.backendpaint.models.shapes;

import java.awt.*;

public class Ellipse extends Shape{

    double vDiameter;
    double hDiameter;

    public double getvDiameter() {
        return vDiameter;
    }

    public void setvDiameter(double vDiameter) {
        this.vDiameter = vDiameter;
    }

    public double gethDiameter() {
        return hDiameter;
    }

    public void sethDiameter(double hDiameter) {
        this.hDiameter = hDiameter;
    }

    public Ellipse() {
        shapePoints = new Point[1];
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
