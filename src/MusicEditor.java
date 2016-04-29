import controller.Controller;
import model.*;
import util.MusicReader;
import util.SongModelCompositionBuilder;
import view.IView;
import view.ViewerFactory;

import java.io.FileReader;


public class MusicEditor {

    public static void main(String[] args) throws Exception{
        MusicReader reader = new MusicReader();
        SongModelCompositionBuilder builder = new SongModelCompositionBuilder();
        Readable readable = new FileReader("mary-little-lamb.txt");
        reader.parseFile(readable, builder);

        // make model
        IModel m = builder.build();

        // make view
        ViewerFactory factory = new ViewerFactory();
        IView v = factory.makeView("composite");
        Controller c = new Controller(m, v);
    }

}
