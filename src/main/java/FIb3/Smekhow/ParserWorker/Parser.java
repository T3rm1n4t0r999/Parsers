package FIb3.Smekhow.ParserWorker;

import org.jsoup.nodes.Document;

public interface Parser<T> {
    T Parse(Document document);
}
