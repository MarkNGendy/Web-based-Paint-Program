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
import java.util.List;

@RestController
@CrossOrigin
public class HomeController {

    @PostMapping("/shapes/add")
    Board addShape(@RequestBody RequestBodyForm requestBodyForm) {
        Board board = Board.getBoard();
        ShapeFactory shapeFactory = ShapeFactory.getShapeFactory();
        ShapeType addedShapeType = requestBodyForm.shape.getShapeType();
        List<Point> addedShapePoints = requestBodyForm.shape.getPoints();
        Shape addedShape = shapeFactory.createShape(addedShapeType, addedShapePoints);
        board.addShape(addedShape);
        return board;
    }


}
