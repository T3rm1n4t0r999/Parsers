package FIb3.Smekhow.KirovDramaTheatre.TheatreRequests;

import FIb3.Smekhow.DataRequest;
import FIb3.Smekhow.KirovDramaTheatre.Model.Performance;
import FIb3.Smekhow.KirovDramaTheatre.Model.Spectacle;
import FIb3.Smekhow.KirovDramaTheatre.Save.AfishaSaveXml;
import FIb3.Smekhow.Save.FileSave;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class RequestSpectaclesAfisha implements DataRequest<Map<Spectacle, List<LocalDateTime>>, ArrayList<Performance>> {
    @Override
    public Map<Spectacle, List<LocalDateTime>> RequestData(ArrayList<Performance> performances) {
        DataRequest<ArrayList<Spectacle>, ArrayList<Performance>> request = new RequestSpectacles();
        List<Spectacle> spectacles = request.RequestData(performances);
        Map<String, List<LocalDateTime>> spectacleDates = performances.stream()
                .collect(Collectors.groupingBy(Performance::getTitle, Collectors.mapping(Performance::getStartDate, Collectors.toList())));
        Map<Spectacle, List<LocalDateTime>> requestedSpectacleDates = spectacles.stream().collect(Collectors.toMap(spectacle -> spectacle, spectacle -> spectacleDates.get(spectacle.getTitle())));
        FileSave xmlSave = new AfishaSaveXml();
        xmlSave.save(requestedSpectacleDates, "ParserDataTheatre/requests/requestSpectaclesAfisha.xml");
        return requestedSpectacleDates;
    }
}
