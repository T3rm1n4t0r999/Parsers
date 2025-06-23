package FIb3.Smekhow.ParserWorker;

import lombok.Getter;
import lombok.Setter;
import org.jsoup.nodes.Document;
import FIb3.Smekhow.ParserWorker.DataHandlerInterfaces.OnCompleted;
import FIb3.Smekhow.ParserWorker.DataHandlerInterfaces.OnNewDataHandler;

import java.io.IOException;
import java.util.ArrayList;


public class ParserWorker<T> {

    @Getter
    @Setter
    Parser<T> parser;
    @Getter
    ParserSettings parserSettings;
    HtmlLoader loader;
    boolean isActive;
    public ArrayList<OnNewDataHandler> onNewDataList;
    public ArrayList<OnCompleted> onCompletedList;
    public ParserWorker(Parser<T> parser) {
        this.parser = parser;
        this.onNewDataList = new ArrayList<>();
        this.onCompletedList = new ArrayList<>();
    }

    public void setParserSettings(ParserSettings parserSettings) {
        this.parserSettings = parserSettings;
        loader = new HtmlLoader(parserSettings);
    }

    public void Start() throws IOException {
        isActive = true;
        Worker();
    }

    public void Abort() {
        isActive = false;
    }

    private void Worker() throws IOException {
        for (int i = parserSettings.getStartPoint(); i <= parserSettings.getEndPoint(); i++) {
            if (!isActive) {
                onCompletedList.get(0).OnCompleted(this);
                return ;
            }
            Document document = loader.GetSourceByPageId(i);
            T result = parser.Parse(document);
            onNewDataList.get(0).OnNewData(this,result);
        }
        onCompletedList.get(0).OnCompleted(this);
        isActive = false;
    }

}

