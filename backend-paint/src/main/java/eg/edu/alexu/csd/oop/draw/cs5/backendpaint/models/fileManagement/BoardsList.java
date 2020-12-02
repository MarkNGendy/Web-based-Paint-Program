package eg.edu.alexu.csd.oop.draw.cs5.backendpaint.models.fileManagement;

import eg.edu.alexu.csd.oop.draw.cs5.backendpaint.controllers.BoardDTO;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlRootElement(name = "boardsList")
@XmlAccessorType(XmlAccessType.FIELD)
public class BoardsList {
    @XmlElement(name = "boards")
    private List<BoardDTO> boards;

    public BoardsList(List<BoardDTO> boards) {
        this.boards = boards;
    }

    public BoardsList() {
    }

    public List<BoardDTO> getBoards() {
        return boards;
    }

    public void setBoards(List<BoardDTO> boards) {
        this.boards = boards;
    }



}
