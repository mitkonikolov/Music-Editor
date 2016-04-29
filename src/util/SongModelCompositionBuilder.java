package util;

import model.IModel;
import model.Note;
import model.Sign;
import model.SongModel;

/**
 * Created by Mitko on 3/18/2016.
 */
public class SongModelCompositionBuilder implements CompositionBuilder<IModel> {

    public IModel model;

    public SongModelCompositionBuilder() {
        this.model = new SongModel();
    }

    @Override
    public IModel build() {
        return this.model;
    }

    @Override
    public CompositionBuilder<IModel> setTempo(int tempo) {
        this.model.setTempo(tempo);
        return this;
    }

    @Override
    public CompositionBuilder<IModel> addNote(int start,
                                              int end,
                                              int instrument,
                                              int pitch,
                                              int volume) {
        Note temp = new Note(start, end, instrument, pitch, volume);
        this.model.add(temp);
        return this;
    }

    @Override
    public CompositionBuilder<IModel> addStartRep(Sign s) {
        this.model.addStartRep(s);
        return this;
    }

    @Override
    public CompositionBuilder<IModel> addContRep(Sign s) {
        this.model.addContRep(s);
        return this;
    }

    @Override
    public CompositionBuilder<IModel> addSpecRep(Sign s) {
        this.model.addSpecRep(s);
        return this;
    }
}
