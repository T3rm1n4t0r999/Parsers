package FIb3.Smekhow.KirovDramaTheatre.TheatreRequests;

import FIb3.Smekhow.DataRequest;
import FIb3.Smekhow.KirovDramaTheatre.Model.Performance;
import FIb3.Smekhow.KirovDramaTheatre.Model.Spectacle;
import FIb3.Smekhow.KirovDramaTheatre.Save.PerformanceSaveXml;
import FIb3.Smekhow.KirovDramaTheatre.Save.SpectacleSaveXml;
import FIb3.Smekhow.Save.FileSave;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class RequestSpectacles implements DataRequest<ArrayList<Spectacle>, ArrayList<Performance>> {
    @Override
    public ArrayList<Spectacle> RequestData(ArrayList<Performance> performances) {
        ArrayList<Spectacle> spectacles = (ArrayList<Spectacle>) performances.stream()
                .map(performance -> new Spectacle(
                        performance.getTitle(),
                        performance.getDuration(),
                        performance.getAgeLimit(),
                        performance.getImageURL())
                )
                .filter(distinctByKey(p->p.getTitle())).collect(Collectors.toList());
        FileSave xmlSave = new SpectacleSaveXml();
        xmlSave.save(spectacles, "ParserDataTheatre/requests/requestSpectacles.xml");
        return spectacles;
    }
    public static <T> Predicate<T> distinctByKey(Function<? super T, Object> keyExtractor) {
        Map<Object, Boolean> map = new ConcurrentHashMap<>();
        return t -> map.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
    }
}
