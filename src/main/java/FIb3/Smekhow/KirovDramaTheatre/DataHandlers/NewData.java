package FIb3.Smekhow.KirovDramaTheatre.DataHandlers;

import FIb3.Smekhow.KirovDramaTheatre.KirovDramaTheatre;
import FIb3.Smekhow.KirovDramaTheatre.Model.Performance;
import FIb3.Smekhow.KirovDramaTheatre.Save.PerformanceSaveJson;
import FIb3.Smekhow.ParserWorker.DataHandlerInterfaces.OnNewDataHandler;
import FIb3.Smekhow.Save.FileSave;
import lombok.SneakyThrows;

import java.util.ArrayList;

public class NewData implements OnNewDataHandler<ArrayList<Performance>> {

    @SneakyThrows
    @Override
    public void OnNewData(Object sender, ArrayList<Performance> args) {
        FileSave PerformanceSaver = new PerformanceSaveJson();
        PerformanceSaver.save(args, KirovDramaTheatre.PARSER_OUTPUT_POSTS);
    }
}


