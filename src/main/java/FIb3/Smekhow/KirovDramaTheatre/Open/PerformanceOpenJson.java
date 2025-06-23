package FIb3.Smekhow.KirovDramaTheatre.Open;

import FIb3.Smekhow.Habr.LogHandlers.LogHandler;
import FIb3.Smekhow.Habr.Model.Publication;
import FIb3.Smekhow.KirovDramaTheatre.Model.Performance;
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

public class PerformanceOpenJson implements FileOpen<ArrayList<Performance>> {
    private static final Logger LOGGER = Logger.getLogger(FileSave.class.getName());

    @SneakyThrows
    @Override
    public ArrayList<Performance> open(String filename) {
        ArrayList<Performance> performances = new ArrayList<>();
        File file = new File(filename);
        ObjectMapper mapper = new ObjectMapper();
        try {
            ArrayNode fileData;
            if (!mapper.readTree(file).isEmpty()) {
                fileData = (ArrayNode) mapper.readTree(file).get("performances");
                for(JsonNode child : fileData)
                {
                    String title = child.get("title").asText();
                    String imageURL = child.get("playbillURL").asText();
                    String price = child.get("price").asText();
                    int ageLimit = child.get("ageLimit").asInt();
                    int duration = child.get("duration").asInt();
                    LocalDateTime startDate = LocalDateTime.parse(child.get("date").asText());
                    Performance performance = new Performance(
                            title,
                            startDate,
                            duration,
                            ageLimit,
                            imageURL,
                            price);
                    performances.add(performance);
                }
            }
        } catch (Exception ex) {
            LogHandler.log(LOGGER, ex);
        }
        return performances;
    }
}
