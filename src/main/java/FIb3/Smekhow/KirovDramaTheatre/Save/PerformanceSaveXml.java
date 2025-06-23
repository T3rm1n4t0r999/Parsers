package FIb3.Smekhow.KirovDramaTheatre.Save;

import FIb3.Smekhow.Habr.LogHandlers.LogHandler;
import FIb3.Smekhow.KirovDramaTheatre.Model.Performance;
import FIb3.Smekhow.KirovDramaTheatre.Model.Spectacle;
import FIb3.Smekhow.Save.FileSave;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class PerformanceSaveXml implements FileSave<List<Performance>> {
    private static final Logger LOGGER = Logger.getLogger(FileSave.class.getName());
    public void save(List<Performance> performances, String filename) {
        try {
            XmlMapper mapper = new XmlMapper();
            String xmlString = mapper.writeValueAsString(performances);
            File xmlOutput = new File(filename);
            FileWriter fileWriter = new FileWriter(xmlOutput);
            fileWriter.write(xmlString);
            fileWriter.close();
        } catch (Exception ex) {
            LogHandler.log(LOGGER, ex);
        }
    }
}