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
 * Created by Mitko on 4/6/16.
 */
public class CompositeView extends JFrame implements IView {

    private GuiViewFrame gui;
    private MidiViewTimerImpl midi;

    public CompositeView() {
        this.gui = new GuiViewFrame();
        this.midi = new MidiViewTimerImpl();
    }

    @Override
    public void update() {
        this.gui.update();
    }

    @Override
    public void playBeat(int beat) {
        this.gui.playBeat(beat);
        this.midi.playBeat(beat);
    }

    public void updateLine(int beat) {
        this.gui.playBeat(beat);
    }

    @Override
    public JScrollPane getScrollPane() {
        return this.gui.getScrollPane();
    }

    @Override
    public void initialize() {
        this.gui.initialize();
    }

    @Override
    public void initializeFully() {
        this.midi.initializeFully();
    }

    @Override
    public void setStartMusic(HashMap<Integer, Set<Note>> startPiece) {
        this.gui.setStartMusic(startPiece);
        this.midi.setStartMusic(startPiece);
    }

    @Override
    public void setEndMusic(HashMap<Integer, Set<Note>> endPiece) {
        this.gui.setEndMusic(endPiece);
        this.midi.setEndMusic(endPiece);
    }

    @Override
    public void setLength(int length) {
        this.gui.setLength(length);
        this.midi.setLength(length);
    }

    @Override
    public void setHighest(Note highest) {
        this.gui.setHighest(highest);
        this.midi.setHighest(highest);
    }

    @Override
    public void setLowest(Note lowest) {
        this.gui.setLowest(lowest);
        this.midi.setLowest(lowest);
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
        this.gui.setAll(startPiece, endPiece, length, lowest,
                highest, tempo, startRep, contRep, specRep);
        this.midi.setAll(startPiece, endPiece, length, lowest,
                highest, tempo, startRep, contRep, specRep);
    }

    @Override
    public void addKeyListener(KeyListener listener) {
        this.gui.addKeyListener(listener);
    }

    @Override
    public void addMouseListener(MouseListener listener) {
        this.gui.addMouseListener(listener);
    }

    @Override
    public int getWidth() {
        return this.gui.getWidth();
    }

    public void repaint() {
        this.gui.repaint();
    }


}
