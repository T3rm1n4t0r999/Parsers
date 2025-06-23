package FIb3.Smekhow.Habr.Save;

import FIb3.Smekhow.Save.FileSave;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.Setter;
import FIb3.Smekhow.Habr.LogHandlers.LogHandler;
import FIb3.Smekhow.Habr.Model.Publication;

import java.io.File;
import java.util.ArrayList;
import java.util.logging.Logger;

@Setter
public class PublicationSaveJson implements FileSave<ArrayList<Publication>> {
    private static final Logger LOGGER = Logger.getLogger(FileSave.class.getName());
    public void save(ArrayList<Publication> publications, String filename) {
        File file = new File(filename);
        ObjectMapper mapper = new ObjectMapper();
        try {
            ArrayNode fileData;
            if(mapper.readTree(file).isEmpty())
                fileData = mapper.createArrayNode();
            else fileData = (ArrayNode) mapper.readTree(file).get("publications");
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
            rootNode.put("publications", fileData);
            mapper.writeValue(file, rootNode);
        } catch (Exception ex) {
            LogHandler.log(LOGGER, ex);
        }
    }
}
