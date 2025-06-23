package FIb3.Smekhow.Habr;

import FIb3.Smekhow.Habr.Model.Publication;
import FIb3.Smekhow.ParsingProgram;
import lombok.SneakyThrows;
import FIb3.Smekhow.Habr.DataHandlers.Completed;
import FIb3.Smekhow.Habr.DataHandlers.NewData;
import FIb3.Smekhow.Habr.HabrParsers.HabrParserArticles;
import FIb3.Smekhow.Habr.HabrParsers.HabrParserNews;
import FIb3.Smekhow.Habr.HabrParsers.HabrParserPosts;
import FIb3.Smekhow.Habr.HabrSettings.HabrSettingsArticles;
import FIb3.Smekhow.Habr.HabrSettings.HabrSettingsNews;
import FIb3.Smekhow.Habr.HabrSettings.HabrSettingsPosts;
import FIb3.Smekhow.ParserWorker.ParserWorker;

import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Habr implements ParsingProgram {
    public static String PARSER_OPTION;
    public static final String PARSER_DIRECTORY = "ParserDataHabr/";
    public static String PARSER_OUTPUT_POSTS;
    public static String PARSER_OUTPUT_COVERS;
    public static final String PARSER_OUTPUT_LOG = PARSER_DIRECTORY  + "Logs/";
    @SneakyThrows
    public void runProgram() {
        PARSER_OPTION = getOption();
        PARSER_OUTPUT_POSTS = PARSER_DIRECTORY + PARSER_OPTION + ".json";
        PARSER_OUTPUT_COVERS = PARSER_DIRECTORY + PARSER_OPTION + "_covers/";
        new FileWriter(PARSER_OUTPUT_POSTS, false).close();
        int start = 1;
        int end = 50;
        ParserWorker parser = getParser(start, end);
        parser.Start();
    }
    public static ParserWorker getParser(int start, int end) {
        ParserWorker parser;
        switch (PARSER_OPTION)
        {
            case "posts":
            {
                parser = new ParserWorker<>(new HabrParserPosts());
                parser.setParserSettings(new HabrSettingsPosts(start, end));
                break;
            }
            case "news":
            {
                parser = new ParserWorker<>(new HabrParserNews());
                parser.setParserSettings(new HabrSettingsNews(start, end));
                break;
            }
            case "articles":
            {
                parser = new ParserWorker<>(new HabrParserArticles());
                parser.setParserSettings(new HabrSettingsArticles(start, end));
                break;
            }
            default:
            {
                parser = new ParserWorker<>(new HabrParserArticles());
                parser.setParserSettings(new HabrSettingsArticles(start, end));
                break;
            }
        }
        parser.onCompletedList.add(new Completed());
        parser.onNewDataList.add(new NewData());
        return parser;
    }
    public static String getOption() {
        System.out.print("Enter an option: ");
        Scanner scanner = new Scanner(System. in);
        String inputString = scanner. nextLine();
        return inputString;
    }
}
