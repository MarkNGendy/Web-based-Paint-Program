package eg.edu.alexu.csd.oop.draw.cs5.backendpaint.models;

import java.util.ArrayList;
import java.util.List;

public class SaveManager {

    private static SaveManager saveManager;
    private static List<Board> boards;

    private SaveManager() {
        boards = new ArrayList<>();
    }

    public List<Board> getBoards() {
        return boards;
    }

    public static SaveManager getSaveManager() {
        if (saveManager == null) {
            saveManager = new SaveManager();
        }
        return saveManager;
    }

    public void saveBoard(Board board) {
        Board savedBoard = new Board();
        savedBoard.setShapes(board.getShapes());
        boards.add(savedBoard);
    }
}
