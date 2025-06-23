package FIb3.Smekhow.KirovDramaTheatre.DataHandlers;

import FIb3.Smekhow.ParserWorker.DataHandlerInterfaces.OnCompleted;

public class Completed implements OnCompleted {

    @Override
    public void OnCompleted(Object sender) {

        System.out.println("Загрузка закончена");
    }
}