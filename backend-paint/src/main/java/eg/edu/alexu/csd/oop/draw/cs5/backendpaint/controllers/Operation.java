package eg.edu.alexu.csd.oop.draw.cs5.backendpaint.controllers;

public enum Operation {
    CREATE("CREATE"), UPDATE("UPDATE");

    public final String name;

    Operation(String name) {
        this.name = name;
    }
}
