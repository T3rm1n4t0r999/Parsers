package FIb3.Smekhow.KirovDramaTheatre.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Spectacle {
    String title;
    int duration;
    int ageLimit;
    String imageURL;
}
