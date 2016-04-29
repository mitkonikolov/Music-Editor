package view;//package cs3500.music.view;

import model.Note;
import model.Sign;

import javax.swing.*;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ConsoleViewer implements IView {

    private HashMap<Integer, Set<Note>> pieceStart;
    private HashMap<Integer, Set<Note>> pieceEnd;
    private int length;
    private Note lowest;
    private Note highest;

    public ConsoleViewer() {

    }

    public ConsoleViewer(HashMap<Integer, Set<Note>> pieceStart,
                         HashMap<Integer, Set<Note>> pieceEnd,
                         int length,
                         Note lowest,
                         Note highest) {
        this.pieceStart = pieceStart;
        this.pieceEnd = pieceEnd;
        this.length = length;
        this.lowest = lowest;
        this.highest = highest;
    }



    public String print() {
        String s = this.printNotes();
        int end = length;
        Set<Note> currPlaying = new HashSet<>();
        for (int i = 0; i <= end; i++) {
            s += this.printLine(i, currPlaying); //make sure print line starts out with a /n
        }
        return s;
    }


/**
//     * Prints all the notes contained in the SongMode
//     * @return the notes in the SongModel as a String
//
*/

    private String printNotes() {
        String s = "";
        s = this.padEnd(s, ("" + length).length());
        int n = lowest.toVal();
        while (n <= highest.toVal()) {
            s += this.padMiddle(new Note(n).print(), 5);
            n++;
        }
        return s;
    }



/** Prints the given line of beets across all notes in the SongModel
     * @param n the line to print
     * @return the line of beats printed aas a string
      */



    private String printLine(int n, Set<Note> currPlaying) {
        String s = "\n" + this.padFront(n + "", ("" + length).length());

        Set<Note> tempStart = this.pieceStart.get(n);
        Set<Note> tempEnd = this.pieceEnd.get(n);

        if(n==0) {
            currPlaying.addAll(tempStart);
        }
        else if(tempStart!=null){
            currPlaying.addAll(tempStart);
        }





        Set<Integer> intPlaying = new HashSet<>();
        Set<Integer> intStart = new HashSet<>();
        if(currPlaying.size()!= 0) {
            for (Note note : currPlaying) {
                intPlaying.add(note.toVal());
            }
        }
        if(tempStart!=null) {
            for (Note note : tempStart) {
                intStart.add(note.toVal());
            }
        }



        for (int i = this.lowest.toVal(); i <= this.highest.toVal(); i++) {
            /*if (!intPlaying.contains(i)) {
                s += "     ";
            }
            else*/ if (intStart.contains(i)) {
                s += "  X  ";
            }
            else if (intPlaying.contains(i)) {
                s += "  |  ";
            }
            else {
                s += "     ";
            }
        }

        if(tempEnd!=null) {
            for(Note note : tempEnd) {
                currPlaying.remove(note);
            }
        }

        return s;
    }





    private String padEnd(String s, int n) {
        while (s.length() < n) {
            s += " ";
        }
        return s.substring(0, n);
    }

    private String padFront(String s, int n) {
        while (s.length() < n) {
            s = " " + s;
        }
        return s;
    }

    private String padMiddle(String s, int n) {
        while (s.length() < n) {
            if (s.length() % 2 == 1) {
                s += " ";
            }
            else {
                s = " " + s;
            }
        }
        return s;
    }

    @Override
    public void initialize() {
        System.out.println(this.print());
    }

    @Override
    public void update() {

    }

    @Override
    public void initializeFully() {

    }

    @Override
    public void setStartMusic(HashMap<Integer, Set<Note>> startPiece) {
        this.pieceStart = startPiece;
    }

    @Override
    public void setEndMusic(HashMap<Integer, Set<Note>> endPiece) {
        this.pieceEnd = endPiece;
    }

    @Override
    public void setLength(int length) {
        this.length = length;
    }

    @Override
    public void setHighest(Note highest) {
        this.highest = highest;
    }

    @Override
    public void setLowest(Note lowest) {
        this.lowest = lowest;
    }

    @Override
    public void addKeyListener(KeyListener listener) {

    }

    @Override
    public void addMouseListener(MouseListener listener) {

    }

    @Override
    public void setAll(HashMap<Integer, Set<Note>> startPiece,
                       HashMap<Integer, Set<Note>> endPiece,
                       int length,
                       Note lowest,
                       Note highest,
                       int tempo,
                       List<Sign> startRep,
                       List<Sign> contRep,
                       List<Sign> specRep) {
        this.setStartMusic(startPiece);
        this.setEndMusic(endPiece);
        this.setLength(length);
        this.setLowest(lowest);
        this.setHighest(highest);
    }

    @Override
    public void playBeat(int i) {

    }

    @Override
    public void updateLine(int i) {

    }

    @Override
    public JScrollPane getScrollPane() {
        return null;
    }

    @Override
    public int getWidth() {
        return (highest.toVal()-lowest.toVal())*ConcreteGuiViewPanel.sqr;
    }

    @Override
    public void repaint() {

    }


   /* // for testing
    public static void main(String[] args) {
        MusicReader reader = new MusicReader();
        SongModelCompositionBuilder builder = new SongModelCompositionBuilder();
        Readable readable = new StringReader("");
        try {
            readable = new FileReader("mary-little-lamb.txt");
        }
        catch (Exception e) {

        }
        reader.parseFile(readable, builder);
        ViewerFactory factory = new ViewerFactory();
        //ConsoleViewer viewer = new ConsoleViewer(builder.model);
        //viewer.initialize();
    }*/

}
