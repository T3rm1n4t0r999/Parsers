package FIb3.Smekhow.KirovDramaTheatre.TheatreParsers;

import FIb3.Smekhow.Habr.HabrParsers.HabrParserPosts;
import FIb3.Smekhow.KirovDramaTheatre.LogHandlers.LogHandler;
import FIb3.Smekhow.KirovDramaTheatre.Model.Performance;
import FIb3.Smekhow.ParserWorker.Parser;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.time.LocalDateTime;
import java.util.*;
import java.util.logging.Logger;

public class KirovDramaTheatreParser implements Parser<ArrayList<Performance>> {
    private static final Logger LOGGER = Logger.getLogger(HabrParserPosts.class.getName());
    public ArrayList<String> months = new ArrayList<>(Arrays.asList("Январь", "Февраль", "Март", "Апрель", "Май", "Июнь","Июль", "Август", "Сентябрь", "Октябрь", "Ноябрь", "Декабрь"));
    @Override
    public ArrayList<Performance> Parse(Document document) {
        ArrayList<Performance> performances = new ArrayList<>();
        int month =0, year =0;
        Elements performanceBlocks = document.getElementsByAttributeValue("id", "list_afisha").first().children();
        for (Element performanceBlock : performanceBlocks) {
            if(performanceBlock.hasClass("t_afisha"))
            {
                try {
                    String title = performanceBlock.child(4).select("a").first().firstChild().toString();
                    int day = Integer.parseInt(performanceBlock.child(0).child(0).text());
                    int hour = Integer.parseInt(performanceBlock.child(0).child(2).text().substring(0, 2));
                    int minute = Integer.parseInt(performanceBlock.child(0).child(2).text().substring(3, 5));
                    LocalDateTime startDate  = LocalDateTime.of(year, month, day, hour,minute);
                    String[] durationParts = performanceBlock.child(4).getElementsByAttributeValue("class", "name_hrono").first().parent().lastChild().toString().split(" ");
                    int duration = 0;
                    if(durationParts.length==2)
                    {
                        if(durationParts[1].equals("час")) duration = Integer.parseInt(durationParts[0]) * 60;
                        else duration = Integer.parseInt(durationParts[0]);
                    }
                    else duration = Integer.parseInt(durationParts[0]) * 60 + Integer.parseInt(durationParts[2]);
                    int ageLimit = Integer.parseInt(performanceBlock.child(4).select("a").first().child(0).text().replace("+",""));
                    String imageURL = "https://kirovdramteatr.ru/" + performanceBlock.child(2).getElementsByTag("img").attr("src");
                    String price = performanceBlock.getElementsByAttributeValue("class", "price_s").first().lastChild().toString();
                    Performance performance = new Performance(
                            title,
                            startDate,
                            duration,
                            ageLimit,
                            imageURL,
                            price);
                    performances.add(performance);
                } catch (Exception ex) {
                    LogHandler.log(LOGGER, ex);
                }
            }
            else if (performanceBlock.hasClass("h2"))
            {
                String monthYear = performanceBlock.select("p").text();
                year = Integer.parseInt(monthYear.substring(monthYear.length()-4));
                month = months.indexOf(monthYear.substring(0, monthYear.length()-5))+1;
            }
        }
        return performances;
    }
}
