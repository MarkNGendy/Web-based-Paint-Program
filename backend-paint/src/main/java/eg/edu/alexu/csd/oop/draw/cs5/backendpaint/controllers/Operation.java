package eg.edu.alexu.csd.oop.draw.cs5.backendpaint.controllers;

public enum Operation {
    DELETE("DELETE"), MOVE("MOVE"), COPY("COPY"), CREATE("CREATE");

    public final String name;

    Operation(String name) {
        this.name = name;
    }
}
