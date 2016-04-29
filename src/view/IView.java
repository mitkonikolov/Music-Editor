package view;

import model.Note;
import model.Sign;

import javax.swing.*;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

/**
 * Created by Mitko on 3/16/2016.
 */
public interface IView {

    void initialize();

    void update();

    void initializeFully();

    void setStartMusic(HashMap<Integer, Set<Note>> startPiece);
    void setEndMusic(HashMap<Integer, Set<Note>> endPiece);

    void setLength(int length);

    void setHighest(Note highest);

    void setLowest(Note lowest);

    void addKeyListener(KeyListener listener);

    void addMouseListener(MouseListener listener);

    void setAll(HashMap<Integer, Set<Note>> startPiece,
                HashMap<Integer, Set<Note>> endPiece,
                int length,
                Note lowest,
                Note highest,
                int tempo,
                List<Sign> startRep,
                List<Sign> contRep,
                List<Sign> specRep);

    void playBeat(int i);

    void updateLine(int i);

    JScrollPane getScrollPane();

    int getWidth();

    void repaint();



}
