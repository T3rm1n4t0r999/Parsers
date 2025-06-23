package FIb3.Smekhow.Habr.DataHandlers;

import FIb3.Smekhow.Habr.Save.PublicationSaveBookCover;
import FIb3.Smekhow.Habr.Save.PublicationSaveJson;
import FIb3.Smekhow.Save.ImageSave;
import FIb3.Smekhow.Save.FileSave;
import lombok.SneakyThrows;
import FIb3.Smekhow.Habr.Habr;
import FIb3.Smekhow.Habr.Model.Publication;
import FIb3.Smekhow.ParserWorker.DataHandlerInterfaces.OnNewDataHandler;


import java.util.ArrayList;

public class NewData implements OnNewDataHandler<ArrayList<Publication>> {

    @SneakyThrows
    @Override
    public void OnNewData(Object sender, ArrayList<Publication> args) {
        FileSave publicationSaver = new PublicationSaveJson();
        publicationSaver.save(args, Habr.PARSER_OUTPUT_POSTS);
        ImageSave imageSaver = new PublicationSaveBookCover();
        imageSaver.download(args, Habr.PARSER_OUTPUT_COVERS);
    }
}


