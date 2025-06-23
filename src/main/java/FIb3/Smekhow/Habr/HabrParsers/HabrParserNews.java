package FIb3.Smekhow.Habr.HabrParsers;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import FIb3.Smekhow.Habr.LogHandlers.LogHandler;
import FIb3.Smekhow.Habr.Model.Publication;
import FIb3.Smekhow.ParserWorker.Parser;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.logging.Logger;

public class HabrParserNews implements Parser<ArrayList<Publication>> {
    private static final Logger LOGGER = Logger.getLogger(HabrParserPosts.class.getName());
    @Override
    public ArrayList<Publication> Parse(Document document) {
        ArrayList<Publication> publications = new ArrayList<Publication>();
        Elements articleBlocks = document.getElementsByAttributeValue("class", "tm-articles-list__item");
        for (Element articleBlock : articleBlocks) {
            try {
                String title = articleBlock.getElementsByAttributeValue("data-test-id", "articleTitle").first().text();
                int readingTime = Integer.parseInt(articleBlock.getElementsByAttributeValue("class", "tm-article-reading-time__label").first().text().replace(" мин", ""));
                String author = articleBlock.getElementsByAttributeValue("class", "tm-user-info__username").text();
                String publishedDateString = articleBlock.getElementsByTag("time").first().attr("title");
                int publishedYear = Integer.parseInt(publishedDateString.substring(0, 4));
                int publishedMonth = Integer.parseInt(publishedDateString.substring(5, 7));
                int publishedDay = Integer.parseInt(publishedDateString.substring(8, 10));
                int publishedHour = Integer.parseInt(publishedDateString.substring(12, 14));
                int publishedMinute = Integer.parseInt(publishedDateString.substring(15, 17));
                LocalDateTime publishedDate  = LocalDateTime.of(publishedYear, publishedMonth, publishedDay,publishedHour,publishedMinute);
                String views = articleBlock.getElementsByAttributeValue("class", "tm-icon-counter__value").first().text();
                String imageURL = "";
                if (articleBlock.getElementsByTag("img").size() > 1)
                    imageURL = articleBlock.getElementsByTag("img").getLast().attr("src");
                Elements tagBlocks = articleBlock.getElementsByAttributeValue("class", "tm-publication-hubs").first().children();
                ArrayList<String> tags = new ArrayList<>();
                for (Element tagBlock : tagBlocks) {
                    tags.add(tagBlock.getElementsByTag("span").first().text());
                }
                String previewText = articleBlock.getElementsByAttributeValueContaining("class", "article-formatted-body").first().text();
                Publication publication = new Publication(
                        title,
                        author,
                        publishedDate,
                        readingTime,
                        views,
                        imageURL,
                        tags,
                        previewText);
                publications.add(publication);
            } catch (Exception ex) {
                LogHandler.log(LOGGER, ex);
            }
        }
        return publications;
    }
}

