package mocks;

import model.Note;
import model.Sign;
import view.IView;

import javax.swing.*;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

/**
 * Created by Mitko on 4/6/2016.
 */
public class MockView implements IView {

    public String output;

    public MockView() {
        output = "New view mock";
    }

    @Override
    public void initialize() {
        output += "\nview initialized";

    }

    @Override
    public void update() {
        output += "\nview updated";
    }

    @Override
    public void initializeFully() {
        output += "\nview initialized fully";
    }

    @Override
    public void setStartMusic(HashMap<Integer, Set<Note>> startPiece) {
        output += "\nstart music updated";
    }

    @Override
    public void setEndMusic(HashMap<Integer, Set<Note>> endPiece) {
        output += "\nend music updated";

    }

    @Override
    public void setLength(int length) {
        output += "\nlength set to " + length;
    }

    @Override
    public void setHighest(Note highest) {
        output += "\nhighest set to " + highest.print();
    }

    @Override
    public void setLowest(Note lowest) {
        output += "\nlowest set to " + lowest.print();

    }

    @Override
    public void addKeyListener(KeyListener listener) {
        output += "\nkey listener added";
    }

    @Override
    public void addMouseListener(MouseListener listener) {
        output += "\nmouse listener added";

    }

    @Override
    public void setAll(HashMap<Integer, Set<Note>> startPiece,
                       HashMap<Integer, Set<Note>> endPiece, int length,
                       Note lowest, Note highest, int tempo,
                       List<Sign> startRep, List<Sign> contRep,
                       List<Sign> specRep) {
        this.setStartMusic(startPiece);
        this.setEndMusic(endPiece);
        this.setLowest(lowest);
        this.setHighest(highest);
        output += "\ntempo set to " + tempo ;
        output += "\n Set of all notes set";
    }

    @Override
    public void playBeat(int i) {
        output += "\nbeat " + i + " played";
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
        return 0;
    }

   // @Override
    public void repaint() {

    }




}
