package FIb3.Smekhow.Habr.HabrSettings;
import FIb3.Smekhow.ParserWorker.ParserSettings;

public class HabrSettingsPosts extends ParserSettings {
    public HabrSettingsPosts(int start, int end) {
        startPoint = start;
        endPoint = end;
        BASE_URL = "https://habr.com/ru/posts";
        PREFIX = "page{CurrentId}";
    }
}