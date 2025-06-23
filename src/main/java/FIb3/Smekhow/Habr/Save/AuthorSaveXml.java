package FIb3.Smekhow.Habr.Save;

import FIb3.Smekhow.Habr.LogHandlers.LogHandler;
import FIb3.Smekhow.Habr.Model.Author;
import FIb3.Smekhow.Habr.Model.Publication;
import FIb3.Smekhow.Save.FileSave;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.logging.Logger;

public class AuthorSaveXml implements FileSave<ArrayList<Author>> {
    private static final Logger LOGGER = Logger.getLogger(FileSave.class.getName());
    public void save(ArrayList<Author> authors, String filename) {
        try {
            XmlMapper mapper = new XmlMapper();
            String xmlString = mapper.writeValueAsString(authors);
            File xmlOutput = new File(filename);
            FileWriter fileWriter = new FileWriter(xmlOutput);
            fileWriter.write(xmlString);
            fileWriter.close();
        } catch (Exception ex) {
            LogHandler.log(LOGGER, ex);
        }
    }
}
