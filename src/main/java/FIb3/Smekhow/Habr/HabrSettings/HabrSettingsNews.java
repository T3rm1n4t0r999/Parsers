package FIb3.Smekhow.Habr.HabrSettings;
import FIb3.Smekhow.ParserWorker.ParserSettings;

public class HabrSettingsNews extends ParserSettings {
    public HabrSettingsNews(int start, int end) {
        startPoint = start;
        endPoint = end;
        BASE_URL = "https://habr.com/ru/news";
        PREFIX = "page{CurrentId}";
    }
}