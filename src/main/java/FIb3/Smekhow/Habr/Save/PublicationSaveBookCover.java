package FIb3.Smekhow.Habr.Save;

import FIb3.Smekhow.Habr.LogHandlers.LogHandler;
import FIb3.Smekhow.Habr.Model.Publication;
import FIb3.Smekhow.Save.ImageSave;

import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.logging.Logger;

public class PublicationSaveBookCover implements ImageSave<ArrayList<Publication>>
{
    private static final Logger LOGGER = Logger.getLogger(PublicationSaveBookCover.class.getName());
    public void download(ArrayList<Publication> publications, String DirectoryPath) {
        //String currentDirectory = System.getProperty("user.dir");
        String restrictedChars = "/\\:?*\"<>|";
        for(Publication publication : publications)
        {
            if (!publication.getImageURL().isEmpty()){
                String title = publication.getTitle();
                for (char restrictedChar : restrictedChars.toCharArray())
                {
                    title = title.replace(restrictedChar, ' ');
                }
                String filePath = DirectoryPath + title + ".jpg";
                File f = new File(filePath);

                if(!f.exists()) {
                    try(InputStream in = new URL(publication.getImageURL()).openStream()){
                        Files.copy(in, Paths.get(filePath));
                    } catch (Exception ex) {
                        LogHandler.log(LOGGER, ex);
                    }
                }
            }
        }
    }
}
