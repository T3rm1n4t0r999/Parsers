package FIb3.Smekhow.Habr.HabrSettings;
import FIb3.Smekhow.ParserWorker.ParserSettings;

public class HabrSettingsArticles extends ParserSettings {
    public HabrSettingsArticles(int start, int end) {
        startPoint = start;
        endPoint = end;
        BASE_URL = "https://habr.com/ru/articles";
        PREFIX = "page{CurrentId}";
    }
}