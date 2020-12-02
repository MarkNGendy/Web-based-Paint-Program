package eg.edu.alexu.csd.oop.draw.cs5.backendpaint.controllers;

import eg.edu.alexu.csd.oop.draw.cs5.backendpaint.models.fileManagement.FileTypes;

public class SaveRequest {
    private String name;
    private FileTypes fileType;


    public SaveRequest(String name, FileTypes fileType) {
        this.name = name;
        this.fileType = fileType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public FileTypes getFileType() {
        return fileType;
    }

    public void setFileType(FileTypes fileType) {
        this.fileType = fileType;
    }
}
