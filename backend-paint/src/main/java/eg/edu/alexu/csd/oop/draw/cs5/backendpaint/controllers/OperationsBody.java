package eg.edu.alexu.csd.oop.draw.cs5.backendpaint.controllers;

public class OperationsBody {
    private Integer shapeIndex;
    private double deltaX;
    private double deltaY;
    private Operation operation;
    private double ratio;

    public double getRatio() {
        return ratio;
    }

    public void setRatio(double ratio) {
        this.ratio = ratio;
    }

    public OperationsBody(Integer shapeIndex, double deltaX, double deltaY, Operation operation) {
        this.shapeIndex = shapeIndex;
        this.deltaX = deltaX;
        this.deltaY = deltaY;
        this.operation = operation;
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

    public Operation getOperation() {
        return this.operation;
    }

    public void setOperation(Operation operation) {
        this.operation = operation;
    }
}
