package eg.edu.alexu.csd.oop.draw.cs5.backendpaint.paintapplication.controllers;

public class UndoRedoBody {
    Integer currBoardIndex;
    UndoRedo choice;

    public UndoRedoBody(Integer currBoardIndex, UndoRedo choice) {
        this.currBoardIndex = currBoardIndex;
        this.choice = choice;
    }
}
