package FIb3.Smekhow.KirovDramaTheatre.Save;

import FIb3.Smekhow.KirovDramaTheatre.LogHandlers.LogHandler;
import FIb3.Smekhow.KirovDramaTheatre.Model.Performance;
import FIb3.Smekhow.Save.ImageSave;
import FIb3.Smekhow.Save.FileSave;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.File;
import java.util.ArrayList;
import java.util.logging.Logger;

public class PerformanceSaveJson implements FileSave<ArrayList<Performance>> {
    private static final Logger LOGGER = Logger.getLogger(ImageSave.class.getName());
    public void save(ArrayList<Performance> performances, String filename) {
        File file = new File(filename);
        ObjectMapper mapper = new ObjectMapper();
        try {
            ArrayNode fileData;
            if(mapper.readTree(file).isEmpty())
                fileData = mapper.createArrayNode();
            else fileData = (ArrayNode) mapper.readTree(file).get("performances");
            ObjectNode rootNode = mapper.createObjectNode();
            for(Performance performance : performances)
            {
                ObjectNode performanceItem = mapper.createObjectNode();
                performanceItem.put("title", performance.getTitle());
                performanceItem.put("date", performance.getStartDate().toString());
                performanceItem.put("duration", performance.getDuration());
                performanceItem.put("ageLimit", performance.getAgeLimit());
                performanceItem.put("playbillURL", performance.getImageURL());
                performanceItem.put("price", performance.getPrice());
                fileData.add(performanceItem);
            }
            rootNode.put("performances", fileData);
            mapper.writeValue(file, rootNode);
        } catch (Exception ex) {
            LogHandler.log(LOGGER, ex);
        }
    }
}
