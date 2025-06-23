package FIb3.Smekhow.Habr.Save;

import FIb3.Smekhow.Habr.LogHandlers.LogHandler;
import FIb3.Smekhow.Habr.Model.Publication;
import FIb3.Smekhow.Save.FileSave;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.dataformat.xml.JacksonXmlModule;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.ser.JavaTimeSerializerModifier;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.logging.Logger;

public class PublicationSaveXml implements FileSave<ArrayList<Publication>> {
    private static final Logger LOGGER = Logger.getLogger(FileSave.class.getName());
    public void save(ArrayList<Publication> publications, String filename) {
        File file = new File(filename);
        XmlMapper mapper = new XmlMapper();
        try {

            ArrayNode fileData = mapper.createArrayNode();
            if(file.length() == 0)
                fileData = mapper.createArrayNode();
            else
                fileData = (ArrayNode) mapper.readValue(file, ArrayNode.class);
            ObjectNode rootNode = mapper.createObjectNode();
            for (Publication publication : publications)
            {
                ObjectNode publicationItem = mapper.createObjectNode();
                ArrayNode publicationTags = mapper.createArrayNode();
                publicationItem.put("title", publication.getTitle());
                publicationItem.put("author", publication.getAuthor());
                publicationItem.put("publicationDate", publication.getPublicationDate().toString());
                publicationItem.put("readingTime", publication.getReadingTime());
                publicationItem.put("views", publication.getViews());
                publicationItem.put("imageURL", publication.getImageURL());
                publicationItem.put("PreviewText", publication.getPreviewText());
                for (String tag : publication.getTags())
                {
                    publicationTags.add(tag);
                }
                publicationItem.put("tags", publicationTags);
                fileData.add(publicationItem);
            }

            rootNode.put("publication", fileData);
            mapper.writeValue(file, rootNode);
        } catch (Exception ex) {
            LogHandler.log(LOGGER, ex);
        }
    }
}
//String xmlString = mapper.writeValueAsString(publications);
//File xmlOutput = new File(filename);
//FileWriter fileWriter = new FileWriter(xmlOutput);
//            fileWriter.write(xmlString);
//            fileWriter.close();