package FIb3.Smekhow.Habr.LogHandlers;

import lombok.SneakyThrows;

import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import static FIb3.Smekhow.Habr.Habr.PARSER_OUTPUT_LOG;


public class LogHandler {
    @SneakyThrows
    static public void log(Logger LOGGER, Exception ex) {
        FileHandler fileHandler = new FileHandler(PARSER_OUTPUT_LOG + "log.txt", false);
        fileHandler.setFormatter(new SimpleFormatter());
        fileHandler.setLevel(Level.ALL);
        LOGGER.addHandler(fileHandler);
        LOGGER.log(Level.INFO, ex.toString(), ex );
    }
}

