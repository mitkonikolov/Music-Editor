package tests;

/**
 * Created by Mitko on 3/21/2016.
 */

import mocks.MessageMock;
import mocks.ReceiverMock;
import model.SongModel;
import org.junit.Test;
import util.MusicReader;
import util.SongModelCompositionBuilder;
import view.*;

import java.io.FileReader;

import static org.junit.Assert.assertEquals;

public class TestMidi {

    ReceiverMock mock;
    SongModel model;
    MidiViewTimerImpl view;
    MusicReader reader;
    SongModelCompositionBuilder builder;
    Readable readable;

    public void initialize(String fileName) {
        mock = new ReceiverMock();
        builder = new SongModelCompositionBuilder();
        view = new MidiViewTimerImpl(mock, new MessageMock());
        reader = new MusicReader();
        try {
            readable = new FileReader(fileName);
        }
        catch (Exception e) { }
        reader.parseFile(readable, builder);
        view.setAll(model.getStartSheetMusic(),
                model.getEndSheetMusic(),
                model.length(),
                model.getLowest(),
                model.getHighest(),
                model.getTempo(),
                model.getStartRep(),
                model.getContRep(),
                model.getSpecRep());
        try {
            for (int i = 0; i <= builder.model.length() && i <= 100; i++) {
                view.playBeat(i);
            }
        }
        catch (Exception e) {

        }
    }

    @Test
    public void testMidiMLL() {
        initialize("mary-little-lamb.txt");
        assertEquals(
                mock.output.contains("note begun at pitch 64 volume 72 at time 0\n") &&
                mock.output.contains("note begun at pitch 55 volume 70 at time 0\n") &&
                mock.output.contains("note ended at pitch 64 volume 72 at time 400000\n") &&
                mock.output.contains("note begun at pitch 62 volume 72 at time 400000\n") &&
                mock.output.contains("note ended at pitch 62 volume 72 at time 800000\n") &&
                mock.output.contains("note begun at pitch 60 volume 71 at time 800000\n" ) &&
                mock.output.contains("note ended at pitch 60 volume 71 at time 1200000\n" ) &&
                mock.output.contains("note begun at pitch 62 volume 79 at time 1200000\n" ) &&
                mock.output.contains("note ended at pitch 55 volume 70 at time 1400000\n" ) &&
                mock.output.contains("note ended at pitch 62 volume 79 at time 1600000\n" ) &&
                mock.output.contains("note begun at pitch 55 volume 79 at time 1600000\n" ) &&
                mock.output.contains("note begun at pitch 64 volume 85 at time 1600000\n" ) &&
                mock.output.contains("note ended at pitch 64 volume 85 at time 2000000\n" ) &&
                mock.output.contains("note begun at pitch 64 volume 78 at time 2000000\n" ) &&
                mock.output.contains("note ended at pitch 64 volume 78 at time 2400000\n" ) &&
                mock.output.contains("note begun at pitch 64 volume 74 at time 2400000\n" ) &&
                mock.output.contains("note ended at pitch 55 volume 79 at time 3000000\n" ) &&
                mock.output.contains("note ended at pitch 64 volume 74 at time 3000000\n" ) &&
                mock.output.contains("note begun at pitch 55 volume 77 at time 3200000\n" ) &&
                mock.output.contains("note begun at pitch 62 volume 75 at time 3200000\n" ) &&
                mock.output.contains("note ended at pitch 62 volume 75 at time 3600000\n" ) &&
                mock.output.contains("note begun at pitch 62 volume 77 at time 3600000\n" ) &&
                mock.output.contains("note ended at pitch 62 volume 77 at time 4000000\n" ) &&
                mock.output.contains("note begun at pitch 62 volume 75 at time 4000000\n" ) &&
                mock.output.contains("note ended at pitch 55 volume 77 at time 4800000\n" ) &&
                mock.output.contains("note ended at pitch 62 volume 75 at time 4800000\n" ) &&
                mock.output.contains("note begun at pitch 55 volume 79 at time 4800000\n" ) &&
                mock.output.contains( "note begun at pitch 64 volume 82 at time 4800000\n" ) &&
                mock.output.contains( "note ended at pitch 55 volume 79 at time 5200000\n" ) &&
                mock.output.contains("note ended at pitch 64 volume 82 at time 5200000\n" ) &&
                mock.output.contains( "note begun at pitch 67 volume 84 at time 5200000\n" ) &&
                mock.output.contains(  "note ended at pitch 67 volume 84 at time 5600000\n" ) &&
                mock.output.contains(   "note begun at pitch 67 volume 75 at time 5600000\n" ) &&
                mock.output.contains("note ended at pitch 67 volume 75 at time 6400000\n" ) &&
                mock.output.contains(    "note begun at pitch 55 volume 78 at time 6400000\n" ) &&
                mock.output.contains(   "note begun at pitch 64 volume 73 at time 6400000\n" ) &&
                mock.output.contains("note ended at pitch 64 volume 73 at time 6800000\n" ) &&
                mock.output.contains("note begun at pitch 62 volume 69 at time 6800000\n" ) &&
                mock.output.contains("note ended at pitch 62 volume 69 at time 7200000\n" ) &&
                mock.output.contains("note begun at pitch 60 volume 71 at time 7200000\n" ) &&
                mock.output.contains("note ended at pitch 60 volume 71 at time 7600000\n" ) &&
                mock.output.contains("note begun at pitch 62 volume 80 at time 7600000\n" ) &&
                mock.output.contains("note ended at pitch 55 volume 78 at time 8000000\n" ) &&
                mock.output.contains("note ended at pitch 62 volume 80 at time 8000000\n" ) &&
                mock.output.contains("note begun at pitch 55 volume 79 at time 8000000\n" ) &&
                mock.output.contains("note begun at pitch 64 volume 84 at time 8000000\n" ) &&
                mock.output.contains("note ended at pitch 64 volume 84 at time 8400000\n" ) &&
                mock.output.contains("note begun at pitch 64 volume 76 at time 8400000\n" ) &&
                mock.output.contains("note ended at pitch 64 volume 76 at time 8800000\n" ) &&
                mock.output.contains("note begun at pitch 64 volume 74 at time 8800000\n" ) &&
                mock.output.contains("note ended at pitch 64 volume 74 at time 9200000\n" ) &&
                mock.output.contains("note begun at pitch 64 volume 77 at time 9200000\n" ) &&
                mock.output.contains("note ended at pitch 55 volume 79 at time 9600000\n" ) &&
                mock.output.contains("note ended at pitch 64 volume 77 at time 9600000\n" ) &&
                mock.output.contains("note begun at pitch 62 volume 75 at time 9600000\n" ) &&
                mock.output.contains("note begun at pitch 55 volume 78 at time 9600000\n" ) &&
                mock.output.contains("note ended at pitch 62 volume 75 at time 10000000\n" ) &&
                mock.output.contains("note begun at pitch 62 volume 74 at time 10000000\n" ) &&
                mock.output.contains("note ended at pitch 62 volume 74 at time 10400000\n" ) &&
                mock.output.contains("note begun at pitch 64 volume 81 at time 10400000\n" ) &&
                mock.output.contains("note ended at pitch 64 volume 81 at time 10800000\n" ) &&
                mock.output.contains("note begun at pitch 62 volume 70 at time 10800000\n" ) &&
                mock.output.contains("note ended at pitch 55 volume 78 at time 11200000\n" ) &&
                mock.output.contains("note ended at pitch 62 volume 70 at time 11200000\n" ) &&
                mock.output.contains("note begun at pitch 52 volume 72 at time 11200000\n" ) &&
                mock.output.contains("note begun at pitch 60 volume 73 at time 11200000\n"),
                true);
        assertEquals(3228, mock.output.length());

    }


}
