package eg.edu.alexu.csd.oop.draw.cs5.backendpaint.models;

import java.util.ArrayList;
import java.util.List;

public class Project {

    private static Project project;
    List<Board> boards;

    private Project() {
        boards = new ArrayList<>();
    }

    public static Project getProject() {
        if (project == null) {
            project = new Project();
        }
        return project;
    }

    public void saveBoard(Board board) {
        boards.add(board);
    }
}
