package eg.edu.alexu.csd.oop.draw.cs5.backendpaint.controllers;

import eg.edu.alexu.csd.oop.draw.cs5.backendpaint.models.Board;
import eg.edu.alexu.csd.oop.draw.cs5.backendpaint.models.shapes.Shape;

public class RequestBodyForm {
    Board board;
    Shape shape;

    public RequestBodyForm(Board board, Shape shape) {
        this.board = board;
        this.shape = shape;
    }
}
