package eg.edu.alexu.csd.oop.draw.cs5.backendpaint.controllers;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class RequestBodyForm {
    ShapeDTO shape;
    Operation operation;

    public RequestBodyForm(ShapeDTO shape, Operation operation) {
        this.shape = shape;
        this.operation = operation;
    }

}
