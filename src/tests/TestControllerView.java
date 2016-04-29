package tests;

import controller.Controller;
import mocks.MockView;
import model.IModel;
import util.MusicReader;
import util.SongModelCompositionBuilder;
import view.ViewerFactory;
import org.junit.Test;

import java.io.FileReader;

import static org.junit.Assert.assertEquals;

/**
 * Created by Mitko on 4/6/2016.
 */
public class TestControllerView {


    @Test
    public void testController() throws Exception{
        MusicReader reader = new MusicReader();
        SongModelCompositionBuilder builder = new SongModelCompositionBuilder();
        Readable readable = new FileReader("mary-little-lamb.txt");
        reader.parseFile(readable, builder);
        // make model
        IModel m = builder.build();

        ViewerFactory factory = new ViewerFactory();
        MockView v = (MockView)factory.makeView("mock");

        Controller c = new Controller(m, v);

        c.play0();
        assertEquals("New view mock\n" +
                "key listener added\n" +
                "mouse listener added\n" +
                "start music updated\n" +
                "end music updated\n" +
                "lowest set to E4\n" +
                "highest set to G5\n" +
                "tempo set to 200000\n" +
                " Set of all notes set\n" +
                "view initialized\n" +
                "beat 0 played", v.output);
    }

    @Test
    public void testControllerGetNotes() throws Exception {
        MusicReader reader = new MusicReader();
        SongModelCompositionBuilder builder = new SongModelCompositionBuilder();
        Readable readable = new FileReader("mary-little-lamb.txt");
        reader.parseFile(readable, builder);
        IModel m = builder.build();
        ViewerFactory factory = new ViewerFactory();
        MockView v = (MockView)factory.makeView("mock");
        Controller c = new Controller(m, v);

        assertEquals("F5", c.getNote(235, 60).print());
        assertEquals(9, c.getBeat(235));
        assertEquals("D5", c.getNote(80, 127).print());
        assertEquals(2, c.getBeat(80));

    }

}
