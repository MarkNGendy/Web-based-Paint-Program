package eg.edu.alexu.csd.oop.draw.cs5.backendpaint.models;

import java.util.ArrayList;
import java.util.List;

public class SaveManager {

    private static SaveManager saveManager;
    private static List<Board> boards;
    private int currBoardIndex;
    private int maxRedoIndex;

    public int getCurrBoardIndex() {
        return currBoardIndex;
    }

    public void setCurrBoardIndex(int currBoardIndex) {
        this.currBoardIndex = currBoardIndex;
    }

    public int getMaxRedoIndex() {
        return maxRedoIndex;
    }

    public void setBoards(List<Board> boards) {
        SaveManager.boards = boards;
    }

    private SaveManager() {
        boards = new ArrayList<>();
        currBoardIndex = -1;
    }

    public List<Board> getBoards() {
        return boards;
    }

    public static SaveManager getSaveManager() {
        if (saveManager == null) {
            saveManager = new SaveManager();
            saveManager.saveBoard(new Board());
        }
        return saveManager;
    }

    public void saveBoard(Board board) {
        Board savedBoard = new Board();
        savedBoard.setShapes(board.getShapes());
        if (currBoardIndex + 1 < boards.size())
            boards.set(currBoardIndex + 1, savedBoard);
        else
            boards.add(savedBoard);
        currBoardIndex++;
        maxRedoIndex = currBoardIndex;
    }

}
