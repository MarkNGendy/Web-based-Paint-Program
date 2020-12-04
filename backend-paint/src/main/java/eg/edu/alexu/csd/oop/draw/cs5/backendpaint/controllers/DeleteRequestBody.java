package eg.edu.alexu.csd.oop.draw.cs5.backendpaint.controllers;

public class DeleteRequestBody {
    int shapeIndex;

    public int getShapeIndex() {
        return shapeIndex;
    }

    public void setShapeIndex(int shapeIndex) {
        this.shapeIndex = shapeIndex;
    }

    public DeleteRequestBody(int shapeIndex) {
        this.shapeIndex = shapeIndex;
    }
}
