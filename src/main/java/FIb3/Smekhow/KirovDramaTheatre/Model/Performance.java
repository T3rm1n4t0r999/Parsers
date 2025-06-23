package FIb3.Smekhow.KirovDramaTheatre.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Performance {
    String title;
    LocalDateTime startDate;
    int duration;
    int ageLimit;
    String imageURL;
    String price;
}
