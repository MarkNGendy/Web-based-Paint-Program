package eg.edu.alexu.csd.oop.draw.cs5.backendpaint.models;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONException;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.bind.JAXBException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SaveManager {

    private static SaveManager saveManager;
    private static List<Board> boards;
    private int currBoardIndex;
    private int maxRedoIndex;

    public int getCurrBoardIndex() {
        return currBoardIndex;
    }

    public void setCurrBoardIndex(int currBoardIndex) {
        this.currBoardIndex = currBoardIndex;
    }

    public int getMaxRedoIndex() {
        return maxRedoIndex;
    }

    public static void setBoards(List<Board> boards) {
        SaveManager.boards = boards;
    }

    private SaveManager() {
        boards = new ArrayList<>();
        currBoardIndex = -1;
    }

    public List<Board> getBoards() {
        return boards;
    }

    public static SaveManager getSaveManager() {
        if (saveManager == null) {
            saveManager = new SaveManager();
            saveManager.saveBoard(new Board());
        }
        return saveManager;
    }

    public void saveBoard(Board board) {
        Board savedBoard = new Board();
        savedBoard.setShapes(board.getShapes());
        if (currBoardIndex + 1 < boards.size())
            boards.set(currBoardIndex + 1, savedBoard);
        else
            boards.add(savedBoard);
        currBoardIndex++;
        maxRedoIndex = currBoardIndex;
    }

    public FileWriter saveJson() throws JSONException {
        ObjectMapper objectMapper = new ObjectMapper();
        //  JSONArray jsonArray = new JSONArray();
        //  jsonArray.put(boards);
        String fileName = "C:\\Users\\Dell\\Desktop\\t\\" + randomString() + ".json";
        FileWriter file = null;
        try {
            file = new FileWriter(fileName);
            String json = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(boards);
            file.write(json);
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;
    }

    public void loadJson() {


    }

    public void saveXml() throws ParserConfigurationException, TransformerException, JAXBException, FileNotFoundException, JsonProcessingException {
        DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();
        Document document = documentBuilder.newDocument();
        Element board = document.createElement("Board");
        document.appendChild(board);
        Attr attr = document.createAttribute("Board");
        ObjectMapper objectMapper = new ObjectMapper();
        String xml = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(boards);
        attr.setValue(xml);
        board.setAttributeNode(attr);
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource domSource = new DOMSource(document);
        String fileName = "C:\\Users\\Dell\\Desktop\\t\\" + randomString() + ".xml";
        StreamResult streamResult = new StreamResult(new File(fileName));
        transformer.transform(domSource, streamResult);


    }

    public void loadXml() {

    }

    public String randomString(){
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

        StringBuilder sb = new StringBuilder();

        Random random = new Random();

        int length = 7;

        for(int i = 0; i < length; i++) {
            int index = random.nextInt(alphabet.length());

            char randomChar = alphabet.charAt(index);
            sb.append(randomChar);
        }

        String randomString = sb.toString();
        return randomString.toLowerCase();
    }
}
