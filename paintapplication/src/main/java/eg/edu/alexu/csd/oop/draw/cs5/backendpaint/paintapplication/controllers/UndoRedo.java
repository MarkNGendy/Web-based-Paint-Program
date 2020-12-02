package eg.edu.alexu.csd.oop.draw.cs5.backendpaint.paintapplication.controllers;

public enum UndoRedo {
    UNDO("UNDO"), REDO("REDO");

    public final String nam;

    UndoRedo(String name) {
        this.nam = name;
    }
}
