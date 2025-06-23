package FIb3.Smekhow.Habr.Open;

import FIb3.Smekhow.Habr.LogHandlers.LogHandler;
import FIb3.Smekhow.Habr.Model.Publication;
import FIb3.Smekhow.Open.FileOpen;
import FIb3.Smekhow.Save.FileSave;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import lombok.SneakyThrows;

import java.io.File;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.logging.Logger;

public class PublicationOpenJson implements FileOpen<ArrayList<Publication>>
{
    private static final Logger LOGGER = Logger.getLogger(FileSave.class.getName());

    @SneakyThrows
    @Override
    public ArrayList<Publication> open(String filename) {
        ArrayList<Publication> publications = new ArrayList<>();
        File file = new File(filename);
        ObjectMapper mapper = new ObjectMapper();
        try {
            ArrayNode fileData;
            if (!mapper.readTree(file).isEmpty()) {
                fileData = (ArrayNode) mapper.readTree(file).get("publications");
                for(JsonNode child : fileData)
                {
                    String title = child.get("title").asText();
                    String author = child.get("author").asText();
                    int readingTime = child.get("readingTime").asInt();
                    String views = child.get("views").asText();
                    String previewText = child.get("PreviewText").asText();
                    ArrayList<String> tags = mapper.readerForListOf(String.class).readValue(child.get("tags"));
                    String imageURL = child.get("imageURL").asText();
                    LocalDateTime publishedDate = LocalDateTime.parse(child.get("publicationDate").asText());
                    Publication publication = new Publication(
                            title,
                            author,
                            publishedDate,
                            readingTime,
                            views,
                            imageURL,
                            tags,
                            previewText);
                    publications.add(publication);
                }
            }
        } catch (Exception ex) {
            LogHandler.log(LOGGER, ex);
        }
        return publications;
    }
}
