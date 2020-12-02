package eg.edu.alexu.csd.oop.draw.cs5.backendpaint.controllers;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "BoardDTO")
@XmlAccessorType(XmlAccessType.FIELD)
public class BoardDTO {
    @XmlElement(name = "shapeDTOList")
    private List<ShapeDTO> shapeDTOList;

    public BoardDTO() {
    }

    public BoardDTO(List<ShapeDTO> shapeDTOList) {
        this.shapeDTOList = shapeDTOList;
    }

    public List<ShapeDTO> getShapeDTOList() {
        return shapeDTOList;
    }

    public void setShapeDTOList(List<ShapeDTO> shapeDTOList) {
        this.shapeDTOList = shapeDTOList;
    }
}
