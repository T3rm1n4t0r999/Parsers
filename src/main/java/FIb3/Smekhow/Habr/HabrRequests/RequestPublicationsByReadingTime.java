package FIb3.Smekhow.Habr.HabrRequests;

import FIb3.Smekhow.DataRequest;
import FIb3.Smekhow.Habr.Model.Publication;
import FIb3.Smekhow.Habr.Save.AuthorSaveXml;
import FIb3.Smekhow.Habr.Save.PublicationSaveXml;
import FIb3.Smekhow.Save.FileSave;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class RequestPublicationsByReadingTime implements DataRequest<ArrayList<Publication>, ArrayList<Publication>> {
    @Override
    public ArrayList<Publication> RequestData(ArrayList<Publication> publications)
    {
        final float readingTimeAVG = publications.stream().mapToInt(Publication::getReadingTime).sum() / (float)publications.size();
        ArrayList<Publication> requestedPublications = (ArrayList<Publication>) publications.stream()
                .filter(publication -> (float)publication.getReadingTime() < readingTimeAVG)
                .collect(Collectors.toList());
        FileSave xmlSave = new PublicationSaveXml();
        xmlSave.save(requestedPublications, "ParserDataHabr/requests/requestPublicationsByReadingTime.xml");
        return requestedPublications;
    }
}
