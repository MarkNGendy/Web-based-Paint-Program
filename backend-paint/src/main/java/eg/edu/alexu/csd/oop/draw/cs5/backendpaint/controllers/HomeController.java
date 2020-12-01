package eg.edu.alexu.csd.oop.draw.cs5.backendpaint.controllers;


import eg.edu.alexu.csd.oop.draw.cs5.backendpaint.models.Board;
import eg.edu.alexu.csd.oop.draw.cs5.backendpaint.models.Point;
import eg.edu.alexu.csd.oop.draw.cs5.backendpaint.models.SaveManager;
import eg.edu.alexu.csd.oop.draw.cs5.backendpaint.models.ShapeFactory;
import eg.edu.alexu.csd.oop.draw.cs5.backendpaint.models.shapes.*;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
public class HomeController {

    @PostMapping("/shapes/")
    List<ShapeDTO> modifyShapes(@RequestBody RequestBodyForm requestBodyForm) {
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
        requiredShape.setStrokeWidth(reqShapeStWidth);
        requiredShape.setStroke(reqShapeStroke);
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
        System.out.println(saveManager.getCurrBoardIndex());
        System.out.println(saveManager.getBoards().size());
        return shapeToShapeDTO(board);
    }


    public List<ShapeDTO> shapeToShapeDTO (Board board) {
        if (board.getShapes().size() == 0) {
            return new ArrayList<>();
        }
        List<ShapeDTO> retList = new ArrayList<>();
        List<Shape> shapes = board.getShapes();
        for (Shape shape: shapes) {
            ShapeDTO addedItem = new ShapeDTO(shape.getPoints(), shape.getShapeType(), shape.getColour(),
                    shape.getIndexInBoard(), shape.getStroke(), shape.getStrokeWidth());
            if (shape instanceof Square) {
                addedItem.setSideLength(((Square) shape).getSideLength());
            }
            else if (shape instanceof Rectangle) {
                addedItem.setLength(((Rectangle) shape).getLength());
                addedItem.setWidth(((Rectangle) shape).getWidth());
            }
            else if (shape instanceof Circle) {
                addedItem.setRadius(((Circle) shape).getRadius());
            }
            else if (shape instanceof Ellipse) {
                addedItem.sethRadius(((Ellipse) shape).gethRadius());
                addedItem.setvRadius(((Ellipse) shape).getvRadius());
            }
            retList.add(addedItem);
        }
        return retList;
    }

    @CrossOrigin
    @PostMapping("/undo/")
    List<ShapeDTO> undo (@RequestBody UndoRedoBody undoBody) {
        Integer index = undoBody.currBoardIndex;
        SaveManager saveManager = SaveManager.getSaveManager();
            if (index >= 0 && index < saveManager.getBoards().size()) {
                saveManager.setCurrBoardIndex(saveManager.getCurrBoardIndex() - 1);
                return shapeToShapeDTO(saveManager.getBoards().get(index - 1));
            }
            saveManager.setCurrBoardIndex(0);
            return shapeToShapeDTO(new Board());
    }
}
