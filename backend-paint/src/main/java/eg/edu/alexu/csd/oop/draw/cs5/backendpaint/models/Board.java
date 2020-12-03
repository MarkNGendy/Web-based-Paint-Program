package eg.edu.alexu.csd.oop.draw.cs5.backendpaint.models;

import eg.edu.alexu.csd.oop.draw.cs5.backendpaint.models.shapes.Shape;


import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlAccessType;

@XmlRootElement(name = "boardsList")
@XmlAccessorType(XmlAccessType.FIELD)
public class Board {

    @XmlElement(name = "shapes")
    List<Shape> shapes;

    public Board() {
        shapes = new ArrayList<>();
    }

    public List<Shape> getShapes() {
        return shapes;
    }

    public void setShapes(List<Shape> shapes) {
        this.shapes = new ArrayList<>();
        this.shapes.addAll(shapes);
    }

    public void addShape (Shape shape) {
        shape.setIndexInBoard(shapes.size());
        shapes.add(shape);
    }

}
