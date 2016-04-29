package model;

import java.util.*;

/**
 * Created by Mitko on 2/26/2016.
 */
public class SongModel implements IModel {
    private HashMap<Integer, Set<Note>> pieceStart;
    private HashMap<Integer, Set<Note>> pieceEnd;
    private int tempo;
    private Note highest;
    private Note lowest;
    private List<Sign> contRep;
    private List<Sign> startRep;
    private List<Sign> specRep;

    public SongModel() {
        this.pieceStart = new HashMap<>();
        this.pieceEnd = new HashMap<>();
        this.tempo = 60;
        this.highest = null;
        this.lowest = null;
        this.startRep = new ArrayList<>();
        this.contRep = new ArrayList<>();
        this.specRep = new ArrayList<>();
    }

    @Override
    public void add(Note n) {
        Set<Note> s = this.pieceStart.get(n.getStartBeat());
        if(s==null) {
            s = new HashSet<>();
            s.add(n);
        }
        else {
            s.add(n);
        }

        Set<Note> e = this.pieceEnd.get(n.getEndBeat() - 1);
        if(e==null) {
            e = new HashSet<>();
            e.add(n);
        }
        else {
            e.add(n);
        }

        if(highest==null || lowest == null) {
            highest = n;
            lowest = n;
        }
        else {
            if(highest.compareTo(n) < 0) {
                highest = n;
            }
            else if(n.compareTo(lowest) < 0) {
                lowest = n;
            }
        }
        this.pieceStart.put(n.getStartBeat(), s);
        this.pieceEnd.put(n.getEndBeat()-1, e);
    }

    @Override
    public void remove(Note n) {
        Set<Note> s = this.pieceStart.get(n.getStartBeat());
        Set<Note> e = this.pieceEnd.get(n.getEndBeat()-1);
        if(!(s.contains(n) && e.contains(n))) {
            throw new IllegalArgumentException("The given note is not " +
                    "present in the piece.");
        }
        s.remove(n);
        e.remove(n);
    }



    @Override
    public void edit(Note oldNote, Note newNote) {
        this.remove(oldNote);
        this.add(newNote);
    }



    @Override
    public int length() {
        int i = 0;

        Set<Integer> s = this.pieceEnd.keySet();
        for(Integer p : s) {
            if(p>i) {
                i=p;
            }
        }

        return i;
    }

    @Override
    public HashMap<Integer, Set<Note>> getStartSheetMusic() {
        return this.pieceStart;
    }

    @Override
    public HashMap<Integer, Set<Note>> getEndSheetMusic() {
        return this.pieceEnd;
    }

    @Override
    public Note getLowest() {
        return this.lowest;
    }

    @Override
    public Note getHighest() {
        return this.highest;
    }

    @Override
    public int getTempo() {
        return this.tempo;
    }

    @Override
    public void setTempo(int t) {
        this.tempo = t;
    }

    /**
     * Get the note starting at that beat and having that pitch.
     * @param start
     * @param pitch
     * @return
     */
    @Override
    public Note getNoteStartingAt(int start, int pitch) {
        Set<Note> notes = this.pieceStart.get(start);
        if(notes!=null) {
            for (Note n : notes) {
                if (n.toVal() == pitch) {
                    return n;
                }
            }
        }
        return null;
    }

    @Override
    public void addStartRep(Sign s) {
        this.startRep.add(s);
    }

    @Override
    public void addContRep(Sign s) {
        this.contRep.add(s);
    }

    @Override
    public void addSpecRep(Sign s) {
        this.specRep.add(s);
    }

    @Override
    public List<Sign> getStartRep() {
        return this.startRep;
    }

    @Override
    public List<Sign> getContRep() {
        return this.contRep;
    }

    @Override
    public List<Sign> getSpecRep() {
        return this.specRep;
    }
}
