package FIb3.Smekhow.KirovDramaTheatre.Save;

import FIb3.Smekhow.Habr.LogHandlers.LogHandler;
import FIb3.Smekhow.KirovDramaTheatre.Model.Spectacle;
import FIb3.Smekhow.Save.FileSave;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.File;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

public class AfishaSaveXml implements FileSave<Map<Spectacle, List<LocalDateTime>>> {
    private static final Logger LOGGER = Logger.getLogger(FileSave.class.getName());
    public void save(Map<Spectacle, List<LocalDateTime>> afisha, String filename) {
        File file = new File(filename);
        XmlMapper mapper = new XmlMapper();
        try {
            ArrayNode fileData = mapper.createArrayNode();
            ObjectNode rootNode = mapper.createObjectNode();
            afisha.forEach((spectacle, localDateTimes) ->
            {
                ObjectNode spectacleItem = mapper.createObjectNode();
                ArrayNode spectacleDates = mapper.createArrayNode();
                spectacleItem.put("title", spectacle.getTitle());
                spectacleItem.put("duration",  spectacle.getDuration());
                spectacleItem.put("ageLimit", spectacle.getAgeLimit());
                spectacleItem.put("imageURL", spectacle.getImageURL());
                for (LocalDateTime startTime : localDateTimes)
                {
                    spectacleDates.add(String.valueOf(startTime));
                }
                spectacleItem.put("dates", spectacleDates);
                fileData.add(spectacleItem);
            });

            rootNode.put("spectacles", fileData);
            mapper.writeValue(file, rootNode);
        } catch (Exception ex) {
            LogHandler.log(LOGGER, ex);
        }
    }
}
