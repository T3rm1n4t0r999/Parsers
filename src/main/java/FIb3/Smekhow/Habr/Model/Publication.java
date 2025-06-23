package FIb3.Smekhow.Habr.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor

public class Publication {
    private String title;
    private String author;
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonFormat(pattern = "yyyy-MM-dd'T'hh:mm:ss.SSS'Z'")
    private LocalDateTime publicationDate;
    private int readingTime;
    private String  views;
    private String imageURL;
    private ArrayList<String> tags;
    private String previewText;

    public int getViews()
    {
        if (views.charAt(views.length()-1) == 'K') return (int)(Float.parseFloat(views.substring(0, views.length()-1)) * 1000);
        else if (views.charAt(views.length()-1) == 'M') return (int)(Float.parseFloat(views.substring(0, views.length()-1)) * 1000000);
        return Integer.parseInt(views);
    }
}
