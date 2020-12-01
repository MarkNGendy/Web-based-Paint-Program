package eg.edu.alexu.csd.oop.draw.cs5.backendpaint.models;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class SaveManager {

    private static SaveManager saveManager;
    private static List<Board> boards;

    public static void setBoards(List<Board> boards) {
        SaveManager.boards = boards;
    }

    private SaveManager() {
        boards = new ArrayList<>();
    }

    public List<Board> getBoards() {
        return boards;
    }

    public static SaveManager getSaveManager() {
        if (saveManager == null) {
            saveManager = new SaveManager();
        }
        return saveManager;
    }

    public void saveBoard(Board board) {
        Board savedBoard = new Board();
        savedBoard.setShapes(board.getShapes());
        boards.add(savedBoard);
    }

    public void saveJson(String fileName , String filePath ){
        try{
            ObjectMapper mapper = new ObjectMapper();
            mapper.writeValue(new File(filePath), boards);
        }
        catch(FileNotFoundException e){
            e.printStackTrace();
        }
        catch(JsonParseException e){
            e.printStackTrace();
        }
        catch(JsonMappingException e){
            e.printStackTrace();
        } catch (JsonGenerationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadJson(String fileName , String filePath){
        try{
            ObjectMapper mapper = new ObjectMapper();
            InputStream inputStream = new FileInputStream(new File(filePath));
            TypeReference<List<Board>> typeReference = new TypeReference<List<Board>>() {
            };
            List<Board>loadBoard= mapper.readValue(inputStream,typeReference);
            setBoards(loadBoard);
            inputStream.close();
        }
        catch(FileNotFoundException e){
            e.printStackTrace();
        }
        catch(JsonParseException e){
            e.printStackTrace();
        }
        catch(JsonMappingException e){
            e.printStackTrace();
        } catch (JsonGenerationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveXml(String fileName , String filePath){

    }

    public void loadXml(String fileName, String filePath) {

    }
}
