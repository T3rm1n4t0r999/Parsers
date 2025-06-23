package FIb3.Smekhow.Habr.HabrRequests;

import FIb3.Smekhow.DataRequest;
import FIb3.Smekhow.Habr.Model.Publication;
import FIb3.Smekhow.Habr.Save.AuthorSaveXml;
import FIb3.Smekhow.Habr.Save.PublicationSaveXml;
import FIb3.Smekhow.Save.FileSave;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class RequestPublicationsByViews implements DataRequest<ArrayList<Publication>, ArrayList<Publication>> {

    @Override
    public ArrayList<Publication> RequestData(ArrayList<Publication> publications)
    {
        ArrayList<Publication> requestedPublications = (ArrayList<Publication>) publications.stream()
                .filter(publication -> publication.getViews() > 100)
                .sorted(Comparator.comparing(Publication::getTitle))
                .collect(Collectors.toList());
        FileSave xmlSave = new PublicationSaveXml();
        xmlSave.save(requestedPublications, "ParserDataHabr/requests/requestPublicationsByViews.xml");
        return requestedPublications;
    }
}
