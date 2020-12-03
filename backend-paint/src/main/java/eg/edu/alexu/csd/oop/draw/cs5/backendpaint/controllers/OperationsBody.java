package eg.edu.alexu.csd.oop.draw.cs5.backendpaint.controllers;

public class OperationsBody {
    private Integer shapeIndex;
    private double deltaX;
    private double deltaY;

    public OperationsBody(Integer shapeIndex, double deltaX, double deltaY) {
        this.shapeIndex = shapeIndex;
        this.deltaX = deltaX;
        this.deltaY = deltaY;
    }

    public int getShapeIndex() {
        return shapeIndex;
    }

    public void setShapeIndex(Integer shapeIndex) {
        this.shapeIndex = shapeIndex;
    }

    public double getDeltaX() {
        return deltaX;
    }

    public void setDeltaX(double deltaX) {
        this.deltaX = deltaX;
    }

    public double getDeltaY() {
        return deltaY;
    }

    public void setDeltaY(double deltaY) {
        this.deltaY = deltaY;
    }
}
