package FIb3.Smekhow.Habr.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class Author {
    private String name;
    private List<Publication> publications;
}
