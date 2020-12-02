package eg.edu.alexu.csd.oop.draw.cs5.backendpaint.models.shapes;

public enum ShapeType {
    SQUARE("SQUARE"), RECTANGLE("RECTANGLE"), TRIANGLE("TRIANGLE"), ELLIPSE("ELLIPSE"), CIRCLE("CIRCLE"), LINE("LINE");

    public final String name;

    ShapeType(String name) {
        this.name = name;
    }
}
