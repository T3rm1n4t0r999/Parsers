package FIb3.Smekhow.KirovDramaTheatre.TheatreRequests;

import FIb3.Smekhow.DataRequest;
import FIb3.Smekhow.Habr.Save.AuthorSaveXml;
import FIb3.Smekhow.KirovDramaTheatre.Model.Performance;
import FIb3.Smekhow.KirovDramaTheatre.Save.PerformanceSaveJson;
import FIb3.Smekhow.KirovDramaTheatre.Save.PerformanceSaveXml;
import FIb3.Smekhow.Save.FileSave;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class RequestPerformancesByAgeDuration implements DataRequest<ArrayList<String>, ArrayList<Performance>> {

    @Override
    public ArrayList<String> RequestData(ArrayList<Performance> performances) {
        ArrayList<String> requestedPerfomances = (ArrayList<String>) performances.stream()
                .filter(performance -> performance.getAgeLimit() == 6)
                .map(Performance::getTitle)
                .distinct()
                .sorted().collect(Collectors.toList());
        FileSave xmlSave = new PerformanceSaveXml();
        xmlSave.save(requestedPerfomances, "ParserDataTheatre/requests/requestPerformancesByAgeDuration.xml");
        return requestedPerfomances;
    }
}
