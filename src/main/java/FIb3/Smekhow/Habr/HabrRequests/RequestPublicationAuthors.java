package FIb3.Smekhow.Habr.HabrRequests;

import FIb3.Smekhow.DataRequest;
import FIb3.Smekhow.Habr.Model.Author;
import FIb3.Smekhow.Habr.Model.Publication;
import FIb3.Smekhow.Habr.Save.AuthorSaveXml;
import FIb3.Smekhow.Save.FileSave;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class RequestPublicationAuthors implements DataRequest<ArrayList<Author>,ArrayList<Publication>>{

    @Override
    public ArrayList<Author> RequestData(ArrayList<Publication> publications) {
        ArrayList<Author> authors = new ArrayList<>();
        publications.stream()
                .collect(Collectors.groupingBy(Publication::getAuthor))
                .forEach((s, publications1) -> {authors.add(new Author(s,publications1));});
        FileSave xmlSave = new AuthorSaveXml();
        xmlSave.save(authors, "ParserDataHabr/requests/requestAuthors.xml");
        return authors;
    }
}
