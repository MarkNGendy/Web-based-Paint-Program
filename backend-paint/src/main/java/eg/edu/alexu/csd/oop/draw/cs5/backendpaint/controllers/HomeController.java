package eg.edu.alexu.csd.oop.draw.cs5.backendpaint.controllers;


import eg.edu.alexu.csd.oop.draw.cs5.backendpaint.models.Board;
import eg.edu.alexu.csd.oop.draw.cs5.backendpaint.models.SaveManager;
import eg.edu.alexu.csd.oop.draw.cs5.backendpaint.models.ShapeFactory;
import eg.edu.alexu.csd.oop.draw.cs5.backendpaint.models.shapes.Line;
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
        ShapeType reqShapeType = requestBodyForm.shape.getShapeType();
        List<Point> reqShapePoints = requestBodyForm.shape.getPoints();
        String reqShapeColour = (requestBodyForm.shape.getColour());
        String reqShapeStroke = (requestBodyForm.shape.getStroke());
        String reqShapeStWidth = (requestBodyForm.shape.getStrokeWidth());
        int indexOfShape = requestBodyForm.shape.getIndexInBoard();
        Shape requiredShape = shapeFactory.createShape(reqShapeType, reqShapePoints);
        requiredShape.setColour(reqShapeColour);
        System.out.println(reqShapeColour);
        requiredShape.setStrokeWidth(reqShapeStWidth);
        System.out.println(reqShapeStWidth);
        requiredShape.setStroke(reqShapeStroke);
        System.out.println(reqShapeStroke);
        requiredShape.setIndexInBoard(indexOfShape);
        SaveManager saveManager = SaveManager.getSaveManager();
        if (saveManager.getBoards().isEmpty()) {
            board = new Board();
        } else {
            board = new Board();
            board.setShapes(saveManager.getBoards().get(saveManager.getBoards().size() - 1).getShapes());
        }
        switch (requestBodyForm.operation) {
            case CREATE:
            case COPY:
                board.addShape(requiredShape);
                break;
            case UPDATE:
                board.getShapes().set(indexOfShape, requiredShape);
                break;
            case DELETE:
                board.getShapes().set(indexOfShape, null);
                break;
        }
        saveManager.saveBoard(board);
        return board;
    }
}
