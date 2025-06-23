package FIb3.Smekhow.Habr.HabrRequests;

import FIb3.Smekhow.DataRequest;
import FIb3.Smekhow.Habr.Model.Publication;
import FIb3.Smekhow.Habr.Save.AuthorSaveXml;
import FIb3.Smekhow.Habr.Save.TagsSaveXml;
import FIb3.Smekhow.Save.FileSave;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class RequestPublicationTags implements DataRequest<List<String>, ArrayList<Publication>> {
    @Override
    public List<String> RequestData(ArrayList<Publication> publications) {
        List<String> publicationTags = publications.stream()
                .map(Publication::getTags)
                .flatMap(List::stream)
                .distinct()
                .collect(Collectors.toList());
        FileSave xmlSave = new TagsSaveXml();
        xmlSave.save(publicationTags, "ParserDataHabr/requests/requestTags.xml");
        return publicationTags;
    }
}
