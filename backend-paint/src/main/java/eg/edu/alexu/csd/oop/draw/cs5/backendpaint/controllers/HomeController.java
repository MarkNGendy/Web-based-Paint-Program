package eg.edu.alexu.csd.oop.draw.cs5.backendpaint.controllers;


import eg.edu.alexu.csd.oop.draw.cs5.backendpaint.models.Board;
import eg.edu.alexu.csd.oop.draw.cs5.backendpaint.models.ShapeFactory;
import eg.edu.alexu.csd.oop.draw.cs5.backendpaint.models.shapes.Shape;
import eg.edu.alexu.csd.oop.draw.cs5.backendpaint.models.shapes.ShapeType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.awt.*;
import java.util.LinkedList;

@RestController
@CrossOrigin
public class HomeController {
    @PostMapping("/add-shape")
    Board modifyBoard(@RequestBody RequestBodyForm requestBodyForm) {
        Board board = new Board(requestBodyForm.board);
        ShapeFactory shapeFactory = new ShapeFactory();
        ShapeType addedShapeType = requestBodyForm.shape.getType();
        LinkedList<Point> addedShapePoints = requestBodyForm.shape.getShapePoints();
        Shape addedShape = shapeFactory.createShape(addedShapeType, addedShapePoints);
        board.addShape(addedShape);
        return board;
    }






}
