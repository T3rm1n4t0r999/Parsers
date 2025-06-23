package FIb3.Smekhow.KirovDramaTheatre;

import FIb3.Smekhow.KirovDramaTheatre.DataHandlers.Completed;
import FIb3.Smekhow.KirovDramaTheatre.DataHandlers.NewData;
import FIb3.Smekhow.KirovDramaTheatre.TheatreParsers.KirovDramaTheatreParser;
import FIb3.Smekhow.KirovDramaTheatre.TheatreSettings.KirovDramaTheatreSettings;
import FIb3.Smekhow.ParsingProgram;
import FIb3.Smekhow.ParserWorker.ParserWorker;
import lombok.SneakyThrows;

import java.io.FileWriter;

public class KirovDramaTheatre implements ParsingProgram {
    public static final String PARSER_OUTPUT_POSTS = "ParserDataTheatre/afisha.json";
    public static final String PARSER_OUTPUT_LOG = "ParserDataTheatre/Logs/log.txt";
    @SneakyThrows
    public void runProgram()
    {
        new FileWriter(PARSER_OUTPUT_POSTS, false).close();
        ParserWorker parser = parser = new ParserWorker<>(new KirovDramaTheatreParser());
        parser.setParserSettings(new KirovDramaTheatreSettings());
        parser.onCompletedList.add(new Completed());
        parser.onNewDataList.add(new NewData());
        parser.Start();
    }

}
