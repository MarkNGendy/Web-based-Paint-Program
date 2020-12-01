package eg.edu.alexu.csd.oop.draw.cs5.backendpaint.controllers;

public enum UndoRedo {
    UNDO("UNDO"),
    REDO("REDO");
    public final String name;
    UndoRedo(String name) {
        this.name = name;
    }
}
