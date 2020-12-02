package eg.edu.alexu.csd.oop.draw.cs5.backendpaint.models.fileManagement;

import com.google.gson.Gson;

import java.io.*;

public class JSONFileManager implements FileManager{
    @Override
    public void save(BoardsList list, String name) {
        String jsonString = new Gson().toJson(list);
        File file = new File(name + ".json");
        FileWriter fileWriter;
        try {
            fileWriter = new FileWriter(file);
            PrintWriter printWriter = new PrintWriter(fileWriter);
            printWriter.print(jsonString);
            printWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public BoardsList load(String name) {
        String jsonString = "";
        try {
            FileReader fileReader = new FileReader(name + ".json");
            BufferedReader reader = new BufferedReader(fileReader);
            StringBuilder stringBuilder = new StringBuilder();
            String line = null;
            String ls = System.getProperty("line.separator");
            while (true) {
                try {
                    if ((line = reader.readLine()) == null) break;
                } catch (IOException e) {
                    e.printStackTrace();
                }
                stringBuilder.append(line);
                stringBuilder.append(ls);
            }
            stringBuilder.deleteCharAt(stringBuilder.length() - 1);
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            jsonString = stringBuilder.toString();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        BoardsList list = new Gson().fromJson(jsonString, BoardsList.class);
        return list;
    }
}
