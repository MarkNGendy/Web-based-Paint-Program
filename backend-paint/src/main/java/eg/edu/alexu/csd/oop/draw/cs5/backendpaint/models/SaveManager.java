package eg.edu.alexu.csd.oop.draw.cs5.backendpaint.models;

import java.util.ArrayList;
import java.util.List;

public class SaveManager {

    private static SaveManager saveManager;
    List<Board> boards;

    private SaveManager() {
        boards = new ArrayList<>();
    }

    public List<Board> getBoards() {
        return boards;
    }

    public static SaveManager getProject() {
        if (saveManager == null) {
            saveManager = new SaveManager();
        }
        return saveManager;
    }

    public void saveBoard(Board board) {
        boards.add(board);
    }
}
