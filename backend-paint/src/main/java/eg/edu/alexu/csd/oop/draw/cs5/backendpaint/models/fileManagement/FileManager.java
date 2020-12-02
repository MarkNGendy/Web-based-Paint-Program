package eg.edu.alexu.csd.oop.draw.cs5.backendpaint.models.fileManagement;


import java.io.File;

public interface FileManager {

    void save(BoardsList list, String name);
    BoardsList load(String name);
}
