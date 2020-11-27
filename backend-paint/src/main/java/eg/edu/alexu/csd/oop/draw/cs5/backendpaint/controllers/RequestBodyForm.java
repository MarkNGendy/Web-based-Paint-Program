package eg.edu.alexu.csd.oop.draw.cs5.backendpaint.controllers;

import eg.edu.alexu.csd.oop.draw.cs5.backendpaint.models.shapes.Shape;

public class RequestBodyForm {
    public enum Operation {
        CREATE, UPDATE, DELETE;
    }

    Shape shape;
    Operation operation;

    public RequestBodyForm(ShapeDTO shape, Operation operation) {
        this.shape = shape;
        this.operation = operation;
    }

}
