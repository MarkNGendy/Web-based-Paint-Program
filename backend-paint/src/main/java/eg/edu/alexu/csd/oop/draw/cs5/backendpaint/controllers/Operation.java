package eg.edu.alexu.csd.oop.draw.cs5.backendpaint.controllers;

public enum Operation {
    CREATE("CREATE"),
    UPDATE("UPDATE"),
    COPY("COPY"),
    DELETE("DELETE");
    public final String name;
    Operation(String name) {
        this.name = name;
    }
}
