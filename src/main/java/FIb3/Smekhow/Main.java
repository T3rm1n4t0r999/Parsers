package FIb3.Smekhow;

import FIb3.Smekhow.Habr.HabrRequests.RequestPublicationAuthors;
import FIb3.Smekhow.Habr.HabrRequests.RequestPublicationTags;
import FIb3.Smekhow.Habr.HabrRequests.RequestPublicationsByReadingTime;
import FIb3.Smekhow.Habr.HabrRequests.RequestPublicationsByViews;
import FIb3.Smekhow.Habr.Model.Author;
import FIb3.Smekhow.Habr.Model.Publication;
import FIb3.Smekhow.Habr.Open.PublicationOpenJson;
import FIb3.Smekhow.KirovDramaTheatre.Model.Performance;
import FIb3.Smekhow.KirovDramaTheatre.Open.PerformanceOpenJson;
import FIb3.Smekhow.KirovDramaTheatre.TheatreRequests.RequestPerformancesByAgeDuration;
import FIb3.Smekhow.KirovDramaTheatre.TheatreRequests.RequestSpectacles;
import FIb3.Smekhow.KirovDramaTheatre.TheatreRequests.RequestSpectaclesAfisha;
import FIb3.Smekhow.Open.FileOpen;
import lombok.SneakyThrows;

import java.util.ArrayList;
import java.util.List;

// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    @SneakyThrows
    public static void main(String[] args) {
//        ParsingProgram parseProgram = new KirovDramaTheatre();
//        parseProgram.runProgram();
        DataRequest request;
        FileOpen<ArrayList<Publication>> jsonOpenHabr = new PublicationOpenJson();
        ArrayList<Publication> publications = jsonOpenHabr.open("ParserDataHabr/news.json");

//        request = new RequestPublicationAuthors();
//        request.RequestData(publications);
//        request = new RequestPublicationsByViews();
//        request.RequestData(publications);
//        request = new RequestPublicationsByReadingTime();
//        request.RequestData(publications);
//        request = new RequestPublicationTags();
//        request.RequestData(publications);

        FileOpen<ArrayList<Performance>> jsonOpenTheatre = new PerformanceOpenJson();
        ArrayList<Performance> performances = jsonOpenTheatre.open("ParserDataTheatre/afisha.json");

//        request = new RequestSpectacles();
//        request.RequestData(performances);
//        request = new RequestPerformancesByAgeDuration();
//        request.RequestData(performances);
        request = new RequestSpectaclesAfisha();
        request.RequestData(performances);
    }

}

