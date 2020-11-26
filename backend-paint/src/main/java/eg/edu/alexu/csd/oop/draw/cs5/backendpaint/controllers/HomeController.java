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
    Board modifyShapes(@RequestBody RequestBodyForm requestBodyForm) {
        Board board;
        ShapeFactory shapeFactory = ShapeFactory.getShapeFactory();
        ShapeType requiredShapeType = requestBodyForm.shape.getShapeType();
        List<Point> requiredShapePoints = requestBodyForm.shape.getPoints();
        Shape requiredShape = shapeFactory.createShape(requiredShapeType, requiredShapePoints);
        int indexOfShape = requestBodyForm.shape.getIndexInBoard();
        Project project = Project.getProject();
        if (project.getBoards().isEmpty()) {
            board = new Board();
        } else {
            board = new Board();
            board.setShapes(project.getBoards().get(project.getBoards().size() - 1).getShapes());
        }
        switch (requestBodyForm.operation) {
            case CREATE -> {
                board.addShape(requiredShape);
                project.saveBoard(board);
            }
            case MOVE, RESIZE, COLOUR -> {
                board.getShapes().set(indexOfShape, requiredShape);
                project.saveBoard(board);
            }
            case DELETE -> {
                board.getShapes().set(indexOfShape, null);
                project.saveBoard(board);
            }
        }
        return board;
    }
}
