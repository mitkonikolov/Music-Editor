package mocks;

import model.IModel;
import model.Note;
import model.Sign;

import java.util.HashMap;
import java.util.List;
import java.util.Set;

/**
 * Created by Mitko on 4/7/2016.
 */
public class MockModel implements IModel {

    public String output;

    public MockModel(){
        output = "Model Created";
    }

    @Override
    public void add(Note n) {
        output += "\nnote of pitch" + n.print() + " added at " + n.getStartBeat() +
                " ending at " + n.getEndBeat();

    }

    @Override
    public void remove(Note n) {
        output += "\nnote of pitch " + n.print() + " removed at " + n.getStartBeat() +
                " ending at " + n.getEndBeat();

    }

    @Override
    public void edit(Note oldNote, Note newNote) {
        this.remove(oldNote);
        this.add(newNote);
    }

    @Override
    public HashMap<Integer, Set<Note>> getStartSheetMusic() {
        return new HashMap<Integer, Set<Note>>();
    }

    @Override
    public HashMap<Integer, Set<Note>> getEndSheetMusic() {
        return new HashMap<Integer, Set<Note>>();
    }

    @Override
    public Note getLowest() {
        return new Note(0);
    }

    @Override
    public Note getHighest() {
        return new Note(0);
    }

    @Override
    public int length() {
        return 50;
    }

    @Override
    public int getTempo() {
        return 50;
    }

    @Override
    public void setTempo(int t) {
        output += "\ntempo set to " + t;
    }

    @Override
    public Note getNoteStartingAt(int start, int pitch) {
        return new Note(0);
    }

    @Override
    public void addStartRep(Sign s) {
    }

    @Override
    public void addContRep(Sign s) {
    }

    @Override
    public void addSpecRep(Sign s) {
    }

    @Override
    public List<Sign> getStartRep() {
        return null;
    }

    @Override
    public List<Sign> getContRep() {
        return null;
    }

    @Override
    public List<Sign> getSpecRep() {
        return null;
    }
}
