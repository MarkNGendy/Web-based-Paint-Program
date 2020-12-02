package eg.edu.alexu.csd.oop.draw.cs5.backendpaint.models.shapes;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "ShapeType")
@XmlAccessorType(XmlAccessType.FIELD)
public enum ShapeType {
    SQUARE("SQUARE"), RECTANGLE("RECTANGLE"), TRIANGLE("TRIANGLE"), ELLIPSE("ELLIPSE"), CIRCLE("CIRCLE"), LINE("LINE");

    @XmlElement(name = "name")
    public final String name;

    ShapeType(String name) {
        this.name = name;
    }
}
