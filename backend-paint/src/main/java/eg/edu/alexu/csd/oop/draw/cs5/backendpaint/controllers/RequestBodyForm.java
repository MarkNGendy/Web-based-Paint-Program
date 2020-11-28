package eg.edu.alexu.csd.oop.draw.cs5.backendpaint.controllers;

public class RequestBodyForm {
    ShapeDTO shape;
    Operation operation;

    public RequestBodyForm(ShapeDTO shape, Operation operation) {
        this.shape = shape;
        this.operation = operation;
    }


}
