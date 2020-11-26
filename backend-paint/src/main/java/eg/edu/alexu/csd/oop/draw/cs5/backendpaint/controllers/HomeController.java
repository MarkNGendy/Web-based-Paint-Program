package eg.edu.alexu.csd.oop.draw.cs5.backendpaint.controllers;


import eg.edu.alexu.csd.oop.draw.cs5.backendpaint.models.Board;
import eg.edu.alexu.csd.oop.draw.cs5.backendpaint.models.Project;
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

    @PostMapping("/shapes/")
    Board addShape(@RequestBody RequestBodyForm requestBodyForm) {
        Board board = new Board();
        board.setShapes(requestBodyForm.board.getShapes());
        ShapeFactory shapeFactory = ShapeFactory.getShapeFactory();
        ShapeType requiredShapeType = requestBodyForm.shape.getShapeType();
        List<Point> requiredShapePoints = requestBodyForm.shape.getPoints();
        Shape requiredShape = shapeFactory.createShape(requiredShapeType, requiredShapePoints);
        int indexOfShape = requestBodyForm.shape.getIndexInBoard();
        Project project = Project.getProject();
        switch (requestBodyForm.operation) {
            case CREATE:
                board.addShape(requiredShape);
                project.saveBoard(board);
                break;
            case MOVE:
            case RESIZE:
            case COLOUR:
                board.getShapes().set(indexOfShape, requiredShape);
                project.saveBoard(board);
                break;
            case DELETE:
                board.getShapes().set(indexOfShape, null);
                project.saveBoard(board);
                break;
        }
        return board;
    }






}
