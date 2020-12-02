package eg.edu.alexu.csd.oop.draw.cs5.backendpaint.models.fileManagement;



import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class XMLFileManager implements FileManager{
    @Override
    public void save(BoardsList list, String name) {
        File file = new File(name + ".xml");
        JAXBContext context;
        try {
            context = JAXBContext.newInstance(BoardsList.class);
            Marshaller mar= context.createMarshaller();
            mar.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            mar.marshal(list, file);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    @Override
    public BoardsList load(String name) {
        JAXBContext context = null;
        try {
            context = JAXBContext.newInstance(BoardsList.class);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        try {
            return (BoardsList) context.createUnmarshaller()
                        .unmarshal(new FileReader(name + ".xml"));
        } catch (JAXBException | FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
