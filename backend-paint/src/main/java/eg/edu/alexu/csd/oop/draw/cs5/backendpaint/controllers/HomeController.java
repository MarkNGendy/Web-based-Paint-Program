package eg.edu.alexu.csd.oop.draw.cs5.backendpaint.controllers;

import eg.edu.alexu.csd.oop.draw.cs5.backendpaint.models.Board;
import eg.edu.alexu.csd.oop.draw.cs5.backendpaint.models.Point;
import eg.edu.alexu.csd.oop.draw.cs5.backendpaint.models.SaveManager;
import eg.edu.alexu.csd.oop.draw.cs5.backendpaint.models.ShapeFactory;
import eg.edu.alexu.csd.oop.draw.cs5.backendpaint.models.fileManagement.BoardsList;
import eg.edu.alexu.csd.oop.draw.cs5.backendpaint.models.fileManagement.FileManager;
import eg.edu.alexu.csd.oop.draw.cs5.backendpaint.models.fileManagement.JSONFileManager;
import eg.edu.alexu.csd.oop.draw.cs5.backendpaint.models.fileManagement.XMLFileManager;
import eg.edu.alexu.csd.oop.draw.cs5.backendpaint.models.shapes.*;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
public class HomeController {

    @PostMapping("/shapes/")
    public List<ShapeDTO> createShape(@RequestBody RequestBodyForm requestBodyForm) {
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
            board.setShapes(saveManager.getBoards().get(saveManager.getCurrBoardIndex()).getShapes());
        }
        board.addShape(requiredShape);
        saveManager.saveBoard(board);
        return shapeToShapeDTO(board);
    }

    public static List<BoardDTO> boardToBoardDTO(List<Board> boards) {
        List<BoardDTO> boardDTOList = new ArrayList<>();
        for (Board board : boards) {
            List<ShapeDTO> shapes = shapeToShapeDTO(board);
            boardDTOList.add(new BoardDTO(shapes));
        }
        return boardDTOList;
    }

    public static List<Shape> shapeDTOToShape(List<ShapeDTO> list) {
        if (list == null) {
            return new ArrayList<>();
        }
        List<Shape> retList = new ArrayList<>();
        for (ShapeDTO element : list) {
            Shape shape;
            ShapeFactory factory = ShapeFactory.getShapeFactory();
            shape = factory.createShape(element.getShapeType(), element.getPoints());
            shape.setColour(element.getColour());
            shape.setStroke(element.getStroke());
            shape.setStrokeWidth(element.getStrokeWidth());
            shape.setIndexInBoard(element.getIndexInBoard());
            retList.add(shape);
        }
        return retList;
    }

    public static List<Board> boardDTOToBoard(List<BoardDTO> list) {
        List<Board> boards = new ArrayList<>();
        for (BoardDTO board : list) {
            Board newInstance = new Board();
            newInstance.setShapes(shapeDTOToShape(board.getShapeDTOList()));
            boards.add(newInstance);
        }
        return boards;
    }

    public static List<ShapeDTO> shapeToShapeDTO(Board board) {
        if (board.getShapes().size() == 0) {
            return new ArrayList<>();
        }
        List<ShapeDTO> retList = new ArrayList<>();
        List<Shape> shapes = board.getShapes();
        for (Shape shape : shapes) {
            if (shape == null) {
                retList.add(null);
            } else {
                ShapeDTO addedItem = new ShapeDTO(shape.getPoints(), shape.getShapeType(), shape.getColour(),
                        shape.getIndexInBoard(), shape.getStroke(), shape.getStrokeWidth());
                if (shape instanceof Square) {
                    addedItem.setLength(((Rectangle) shape).getLength());
                    addedItem.setWidth(((Rectangle) shape).getWidth());
                    addedItem.setSideLength(((Square) shape).getSideLength());
                } else if (shape instanceof Rectangle) {
                    addedItem.setLength(((Rectangle) shape).getLength());
                    addedItem.setWidth(((Rectangle) shape).getWidth());
                } else if (shape instanceof Circle) {
                    addedItem.setRadius(((Circle) shape).getRadius());
                } else if (shape instanceof Ellipse) {
                    addedItem.sethRadius(((Ellipse) shape).gethRadius());
                    addedItem.setvRadius(((Ellipse) shape).getvRadius());
                }
                retList.add(addedItem);
            }
        }
        return retList;
    }

    @CrossOrigin
    @PostMapping("/undo/")
    public List<ShapeDTO> undoOrRedo(@RequestBody UndoRedoBody undoBody) {
        Integer index = undoBody.currBoardIndex;
        SaveManager saveManager = SaveManager.getSaveManager();
        if (undoBody.choice == UndoRedo.UNDO) {
            if (index > 0 && index <= saveManager.getCurrBoardIndex()) {
                saveManager.setCurrBoardIndex(saveManager.getCurrBoardIndex() - 1);
                return shapeToShapeDTO(saveManager.getBoards().get(index - 1));
            }
            saveManager.setCurrBoardIndex(saveManager.getCurrBoardIndex());
            return shapeToShapeDTO(saveManager.getBoards().get(saveManager.getCurrBoardIndex()));
        }
        if (index >= 0 && index < saveManager.getMaxRedoIndex()) {
            saveManager.setCurrBoardIndex(saveManager.getCurrBoardIndex() + 1);
            return shapeToShapeDTO(saveManager.getBoards().get(index + 1));
        }
        saveManager.setCurrBoardIndex(saveManager.getMaxRedoIndex());
        return shapeToShapeDTO(saveManager.getBoards().get(saveManager.getMaxRedoIndex()));
    }

    @CrossOrigin
    @GetMapping("/clear/")
    public int clear() {
        SaveManager savemanager = SaveManager.getSaveManager();
        if (savemanager.getBoards().get(savemanager.getCurrBoardIndex()).getShapes().isEmpty())
            return 0;
        savemanager.saveBoard(new Board());
        return 1;
    }

    @CrossOrigin
    @PostMapping("/delete/")
    public List<ShapeDTO> delete(@RequestBody OperationsBody operationsBody) {
        Board board;
        SaveManager saveManager = SaveManager.getSaveManager();
        if (saveManager.getBoards().isEmpty()) {
            board = new Board();
        } else {
            board = new Board();
            board.setShapes(saveManager.getBoards().get(saveManager.getCurrBoardIndex()).getShapes());
        }
        if (operationsBody.getShapeIndex() >= 0 && operationsBody.getShapeIndex() < board.getShapes().size())
            board.getShapes().set(operationsBody.getShapeIndex(), null);
        saveManager.saveBoard(board);
        return shapeToShapeDTO(board);
    }

    @PostMapping("/move/")
    public List<ShapeDTO> move(@RequestBody OperationsBody operationsBody) {
        Board board;
        SaveManager saveManager = SaveManager.getSaveManager();
        if (saveManager.getBoards().isEmpty()) {
            board = new Board();
        } else {
            board = new Board();
            board.setShapes(saveManager.getBoards().get(saveManager.getCurrBoardIndex()).getShapes());
        }
        if (operationsBody.getShapeIndex() >= 0 && operationsBody.getShapeIndex() < board.getShapes().size()) {
            Shape requiredShape = board.getShapes().get(operationsBody.getShapeIndex()).deepCopy(board.getShapes().get(operationsBody.getShapeIndex()));
            int i = 0;
            for (Point p: requiredShape.getPoints()) {
                p.setX(p.getX() + operationsBody.getDeltaX());
                p.setY(p.getY() + operationsBody.getDeltaY());
                requiredShape.getPoints().set(i, p);
                i++;
            }
            board.getShapes().set(operationsBody.getShapeIndex(), requiredShape);
        }
        saveManager.saveBoard(board);
        return shapeToShapeDTO(board);
    }

    @PostMapping("/copy/")
    public List<ShapeDTO> copy(@RequestBody OperationsBody operationsBody) {
        System.out.println(operationsBody.getDeltaX() + "\n");
        System.out.println(operationsBody.getDeltaY() + "\n");
        System.out.print(operationsBody.getShapeIndex());
        Board board;
        SaveManager saveManager = SaveManager.getSaveManager();
        if (saveManager.getBoards().isEmpty()) {
            board = new Board();
        } else {
            board = new Board();
            board.setShapes(saveManager.getBoards().get(saveManager.getCurrBoardIndex()).getShapes());
        }
        if (operationsBody.getShapeIndex() >= 0 && operationsBody.getShapeIndex() < board.getShapes().size()) {
            Shape requiredShape = board.getShapes().get(operationsBody.getShapeIndex()).deepCopy(board.getShapes().get(operationsBody.getShapeIndex()));
            int i = 0;
            for (Point p: requiredShape.getPoints()) {
                p.setX(p.getX() + operationsBody.getDeltaX());
                p.setY(p.getY() + operationsBody.getDeltaY());
                requiredShape.getPoints().set(i, p);
                i++;
        if (index >= 0 && index < board.getShapes().size()) {
            Shape requiredShape;
            switch (operationsBody.getOperation()) {
                case MOVE:
                    requiredShape = changeShape(board, operationsBody);
                    board.getShapes().set(index, requiredShape);
                    break;
                case COPY:
                    requiredShape = changeShape(board, operationsBody);
                    board.addShape(requiredShape);
                    requiredShape.setIndexInBoard(board.getShapes().size() - 1);
                    break;
                case DELETE:
                    board.getShapes().set(index, null);
                    break;
                default:
            }
            board.addShape(requiredShape);
            requiredShape.setIndexInBoard(board.getShapes().size() - 1);
        }
        saveManager.saveBoard(board);
        return shapeToShapeDTO(board);
    }

    @PostMapping("/save/")
    public void save(@RequestBody SaveRequest saveRequest) {
        SaveManager saveManager = SaveManager.getSaveManager();
        BoardsList boardsList = new BoardsList(HomeController.boardToBoardDTO(saveManager.getBoards()));
        FileManager saver;
        switch (saveRequest.getFileType()) {
            case XML:
                saver = new XMLFileManager();
                saver.save(boardsList, saveRequest.getName());
                break;
            case JSON:
                saver = new JSONFileManager();
                saver.save(boardsList, saveRequest.getName());
                break;
        }
    }

    @CrossOrigin
    @PostMapping("/load/")
    public List<ShapeDTO> load(@RequestBody SaveRequest saveRequest) {
        System.out.printf("i am here ");
        SaveManager saveManager = SaveManager.getSaveManager();
        BoardsList boardsList = new BoardsList(HomeController.boardToBoardDTO(saveManager.getBoards()));
        FileManager saver;
        List<BoardDTO> boards;
        switch (saveRequest.getFileType()) {
            case XML:
                saver = new XMLFileManager();
                boards = saver.load(saveRequest.getName()).getBoards();
                saveManager.setBoards(boardDTOToBoard(boards));
                return shapeToShapeDTO(saveManager.getBoards().get(saveManager.getBoards().size() - 1));
            case JSON:
                saver = new JSONFileManager();
                boards = saver.load(saveRequest.getName()).getBoards();
                saveManager.setBoards(boardDTOToBoard(boards));
                saveManager.setCurrBoardIndex(saveManager.getBoards().size() - 1);
                return shapeToShapeDTO(saveManager.getBoards().get(saveManager.getBoards().size() - 1));
        }
        return null;
    }

    @CrossOrigin
    @GetMapping("/index/set")
    public int setCurrBoardIndex () {
        return SaveManager.getSaveManager().getCurrBoardIndex();
    }
}
