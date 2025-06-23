package FIb3.Smekhow.ParserWorker;

import lombok.Getter;

public abstract class ParserSettings {

    // Адрес сайта
    public static String BASE_URL;

    // префикс страницы
    public static String PREFIX;

    @Getter
    // начало пагинацииs
    protected int startPoint;
    @Getter
    // конец пагинации
    protected int endPoint;

}


