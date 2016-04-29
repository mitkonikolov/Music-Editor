package controller;

import model.IModel;
import model.Note;
import model.Sign;
import view.ConcreteGuiViewPanel;
import view.IView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Mitko on 3/31/2016.
 */

public class Controller {

    private IModel model;
    private IView view;
    private Mode mode;
    private Note temp;
    private Timer timer;

    public Controller(IModel m, IView v) {
        this.model = m;
        this.view = v;
        this.confiqureKeyMapListener();
        this.confiqureMouseMapListener();
        this.timer = new Timer(this.model.getTempo()/1000, new TimerAction());

        this.view.setAll(model.getStartSheetMusic(),
                model.getEndSheetMusic(),
                model.length(),
                model.getLowest(),
                model.getHighest(),
                model.getTempo(),
                model.getStartRep(),
                model.getContRep(),
                model.getSpecRep());
        this.mode = Mode.Normal;
        this.view.initialize();
    }

    /**
     * Sets up three {@code HashMap objects} which map events to
     * {@code Runnable objects} and passes them to a
     * {@code KeyboardHandler object}.
     */
    private void confiqureKeyMapListener() {
        HashMap<Character, Runnable> keyTyped = new HashMap<>();
        HashMap<Integer, Runnable> keyPressed = new HashMap<>();
        HashMap<Integer, Runnable> keyRelesed = new HashMap<>();

        // set up keyTyped map
        keyTyped.put(' ', new startPausePlaying());
        keyTyped.put('r', new restart());
        keyTyped.put('a', new changeToAddMode());
        keyTyped.put('d', new changeToRemoveMode());
        keyTyped.put('m', new changeToMoveMode());
        keyTyped.put('n', new changeToNormal());
        keyTyped.put('l', new changeToStartRepeatMode());
        keyTyped.put('k', new changeToContRepeatMode());
        keyTyped.put('j', new changeToSpecRepeatMode());

        // set up keyReleased map
        keyRelesed.put(KeyEvent.VK_RIGHT, new moveRight());
        keyRelesed.put(KeyEvent.VK_LEFT, new moveLeft());
        keyRelesed.put(KeyEvent.VK_DOWN, new moveDown());
        keyRelesed.put(KeyEvent.VK_UP, new moveUp());
        keyRelesed.put(KeyEvent.VK_HOME, new GoHome());
        keyRelesed.put(KeyEvent.VK_END, new GoEnd());
        keyRelesed.put(KeyEvent.VK_PAGE_DOWN, new GoDown());
        keyRelesed.put(KeyEvent.VK_PAGE_UP, new GoUp());

        // set up keyPressed map
        keyPressed.put(KeyEvent.VK_RIGHT, new moveRight());
        keyPressed.put(KeyEvent.VK_LEFT, new moveLeft());
        keyPressed.put(KeyEvent.VK_DOWN, new moveDown());
        keyPressed.put(KeyEvent.VK_UP, new moveUp());

        KeyboardHandler kHand = new KeyboardHandler(keyTyped, keyPressed, keyRelesed);

        // pass the maps to the listener
        kHand.setKeyTypedMap(keyTyped);
        kHand.setKeyPressedMap(keyPressed);
        kHand.setKeyReleasedMap(keyRelesed);

        this.view.addKeyListener(kHand);
    }

    /**
     * {@code Runnable object} used to switch between {@code Mode.Normal},
     * {@code Mode.Playing}, and {@code Mode.Pause}.
     */
    private class startPausePlaying implements Runnable {

        @Override
        public void run() {
            if(mode.equals(Mode.Normal)) {
                mode = Mode.Playing;
                view.initializeFully();
                timer.start();
            }
            else if(mode.equals(Mode.Playing)) {
                timer.stop();
                mode = Mode.Pause;
            }
            else if(mode.equals(Mode.Pause)) {
                timer.start();
                mode = Mode.Playing;
            }
        }
    }

    /**
     * {@code Runnable class} restarting the musical piece.
     * It will not change the mode if the musical piece is
     * being currently played.
     */
    private class restart implements Runnable {

        @Override
        public void run() {
            if(!mode.equals(Mode.Playing)) {
                timer.stop();
                timer = new Timer(model.getTempo() / 1000, new TimerAction());
                view.updateLine(0);
                new GoHome().run();
                mode = Mode.Normal;
            }
        }
    }

    /**
     * {@code Runnable class} setting the {@code mode} field to
     * {@code Mode.Normal}. It will not change the {@code mode} if
     * the musical piece is currently being played.
     */
    private class changeToNormal implements Runnable {

        @Override
        public void run() {
            if(!mode.equals(Mode.Playing)) {
                if (temp != null && !mode.equals(Mode.Pickup)) {
                    model.remove(temp);
                    mode = Mode.Normal;
                    view.repaint();
                } else {
                    temp = null;
                    mode = Mode.Normal;
                }
            }
        }
    }

    /**
     * {@code Runnable class} setting the {@code mode} field to
     * {@code Mode.Add}. It will not change the {@code mode} if
     * the musical piece is currently being played.
     */
    private class changeToAddMode implements Runnable {

        @Override
        public void run() {
            if(!mode.equals(Mode.Playing)) {
                mode = Mode.Add;
            }
        }
    }

    /**
     * {@code Runnable class} setting the {@code mode} field to
     * {@code Mode.Remove}. It will not change the {@code mode} if
     * the musical piece is currently being played.
     */
    private class changeToRemoveMode implements Runnable {

        @Override
        public void run() {
            if(!mode.equals(Mode.Playing)) {
                mode = Mode.Remove;
            }
        }
    }

    /**
     * {@code Runnable class} setting the {@code mode} field to
     * {@code Mode.Move}. It will not change the {@code mode} if
     * the musical piece is currently being played.
     */
    private class changeToMoveMode implements Runnable {

        @Override
        public void run() {
            if(!mode.equals(Mode.Playing)) {
                mode = Mode.Move;
            }
        }
    }

    /**
     * {@code Runnable class} setting the {@code mode} field to
     * {@code Mode.StartRepeatMode}. It will not change the {@code mode}
     * if the musical piece is currently being played.
     */
    private class changeToStartRepeatMode implements Runnable {

        @Override
        public void run() {
            if(!mode.equals((Mode.Playing))) {
                mode = Mode.StartRepeatMode;
            }
        }
    }

    /**
     * {@code Runnable class} setting the {@code mode} field to
     * {@code Mode.ContRepeatMode}. It will not change the {@code mode}
     * if the musical piece is currently being played.
     */
    private class changeToContRepeatMode implements Runnable {

        @Override
        public void run() {
            if(!mode.equals(Mode.Playing)) {
                mode = Mode.ContRepeatMode;
            }
        }
    }

    /**
     * {@code Runnable class} setting the {@code mode} field to
     * {@code Mode.SpecRepeatMode}. It will not change the {@code mode}
     * if the musical piece is currently being played.
     */
    private class changeToSpecRepeatMode implements Runnable {

        @Override
        public void run() {
            if(!mode.equals(Mode.Playing)) {
                mode = Mode.SpecRepeatMode;
            }
        }
    }

    /**
     * {@code Runnable object} that adds one to the value of the
     * {@code HorizontalScrollBar} of the {@code JScrollBar} of the
     * {@code JScrollPane} of {@code view} field.
     */
    private class moveRight implements Runnable {

        @Override
        public void run() {
            JScrollPane pane = view.getScrollPane();
            JScrollBar bar = pane.getHorizontalScrollBar();
            if(bar.getValue()+bar.getVisibleAmount()<bar.getMaximum()) {
                bar.setValue(bar.getValue() + 1);
                view.repaint();
            }
        }
    }

    /**
     * {@code Runnable object} that subtracts one from the value of the
     * {@code HorizontalScrollBar} of the {@code JScrollBar} of the
     * {@code JScrollPane} of {@code view} field.
     */
    private class moveLeft implements Runnable {

        @Override
        public void run() {
            JScrollPane pane = view.getScrollPane();
            JScrollBar bar = pane.getHorizontalScrollBar();
            if(bar.getValue()>bar.getMinimum()) {
                bar.setValue(bar.getValue() - 1);
                view.repaint();
            }
        }
    }

    /**
     * {@code Runnable object} that adds one to the value of the
     * {@code VerticalScrollBar} of the {@code JScrollBar} of the
     * {@code JScrollPane} of {@code view} field.
     */
    private class moveDown implements Runnable {

        @Override
        public void run() {
            JScrollPane pane = view.getScrollPane();
            JScrollBar bar = pane.getVerticalScrollBar();
            if(bar.getValue()+bar.getVisibleAmount()<bar.getMaximum()) {
                bar.setValue(bar.getValue() + 1);
                view.repaint();
            }
        }
    }

    /**
     * {@code Runnable object} that subtracts one to the value of the
     * {@code VerticalScrollBar} of the {@code JScrollBar} of the
     * {@code JScrollPane} of {@code view} field.
     */
    private class moveUp implements Runnable {

        @Override
        public void run() {
            JScrollPane pane = view.getScrollPane();
            JScrollBar bar = pane.getVerticalScrollBar();
            if(bar.getValue()>bar.getMinimum()) {
                bar.setValue(bar.getValue() - 1);
                view.repaint();
            }
        }
    }

    /**
     * {@code Runnable object} that sets to minimum the value of the
     * {@code HorizontalScrollBar} of the {@code JScrollBar} of the
     * {@code JScrollPane} of {@code view} field.
     */
    private class GoHome implements Runnable {

        @Override
        public void run() {
            JScrollPane pane = view.getScrollPane();
            JScrollBar bar = pane.getHorizontalScrollBar();
            bar.setValue(bar.getMinimum());
            view.repaint();
        }
    }

    /**
     * {@code Runnable object} that sets to maximum the value of the
     * {@code HorizontalScrollBar} of the {@code JScrollBar} of the
     * {@code JScrollPane} of {@code view} field.
     */
    private class GoEnd implements Runnable {

        @Override
        public void run() {
            JScrollPane pane = view.getScrollPane();
            JScrollBar bar = pane.getHorizontalScrollBar();
            bar.setValue(bar.getMaximum());
            view.repaint();
        }
    }

    /**
     * {@code Runnable object} that sets to maximum the value of the
     * {@code VerticalScrollBar} of the {@code JScrollBar} of the
     * {@code JScrollPane} of {@code view} field.
     */
    private class GoDown implements Runnable {

        @Override
        public void run() {
            JScrollPane pane = view.getScrollPane();
            JScrollBar bar = pane.getVerticalScrollBar();
            bar.setValue(bar.getMaximum());
            view.repaint();
        }
    }

    /**
     * {@code Runnable object} that sets to minimum the value of the
     * {@code VerticalScrollBar} of the {@code JScrollBar} of the
     * {@code JScrollPane} of {@code view} field.
     */
    private class GoUp implements Runnable {

        @Override
        public void run() {
            JScrollPane pane = view.getScrollPane();
            JScrollBar bar = pane.getVerticalScrollBar();
            bar.setValue(bar.getMinimum());
            view.repaint();
        }
    }

    /**
     * {@code Runnable class} that allows the music editor to scroll
     * automatically if the played notes are not currently in the
     * viewable area.
     */
    private class AutoScroll implements Runnable {

        @Override
        public void run() {
            JScrollPane pane = view.getScrollPane();
            JScrollBar bar = pane.getHorizontalScrollBar();
            if(bar.getValue()+bar.getVisibleAmount()<(bar.getMaximum()-20)) {
                bar.setValue(bar.getValue() + 20);
                view.repaint();
            }
        }
    }

    /**
     * {@code Class} that acts as a Runnable object. It performs different
     * actions depending on the combination of the {@code Mode} of the
     * {@code Controller} and the {@code MouseEvent}l
     */
    class MouseRunnable {

        void run(int x, int y) {
            Note temp2;
            if(mode.equals(Mode.Add)) {
                temp = getNote(x, y);
                model.add(temp);
                view.setAll(model.getStartSheetMusic(),
                        model.getEndSheetMusic(),
                        model.length(),
                        model.getLowest(),
                        model.getHighest(),
                        model.getTempo(),
                        model.getStartRep(),
                        model.getContRep(),
                        model.getSpecRep());
                mode = Mode.Putdown;
                view.repaint();
            }
            else if(mode.equals(Mode.Putdown)) {
                int n = y / ConcreteGuiViewPanel.sqr - 1;
                int pitch = model.getHighest().toVal() - n;
                if(pitch==temp.toVal()) {
                    temp2 = new Note(temp.getStartBeat(), getBeat(x), 1,
                            pitch, 75);
                    model.remove(temp);
                    model.add(temp2);
                    mode = Mode.Normal;
                    view.setAll(model.getStartSheetMusic(),
                            model.getEndSheetMusic(),
                            model.length(),
                            model.getLowest(),
                            model.getHighest(),
                            model.getTempo(),
                            model.getStartRep(),
                            model.getContRep(),
                            model.getSpecRep());
                    view.repaint();
                }
            }
            else if(mode.equals(Mode.Remove)) {
                temp2 = model.getNoteStartingAt(getBeat(x), getPitch(y));
                if(temp2!=null) {
                    model.remove(temp2);
                    mode = Mode.Normal;
                    view.setAll(model.getStartSheetMusic(),
                            model.getEndSheetMusic(),
                            model.length(),
                            model.getLowest(),
                            model.getHighest(),
                            model.getTempo(),
                            model.getStartRep(),
                            model.getContRep(),
                            model.getSpecRep());
                    view.repaint();
                }
            }
            else if(mode.equals(Mode.Move)) {
                temp = model.getNoteStartingAt(getBeat(x), getPitch(y));
                if(temp!=null) {
                    mode = Mode.Pickup;
                }
            }
            else if(mode.equals(Mode.Pickup)) {
                int xBeat = getBeat(x);
                int pitch = getPitch(y);
                Note p = new Note(xBeat,
                        (temp.getEndBeat()-temp.getStartBeat())+xBeat,
                        temp.getInstrument(), pitch, temp.getVolume());
                model.remove(temp);
                model.add(p);
                mode = Mode.Normal;
                view.setAll(model.getStartSheetMusic(),
                        model.getEndSheetMusic(),
                        model.length(),
                        model.getLowest(),
                        model.getHighest(),
                        model.getTempo(),
                        model.getStartRep(),
                        model.getContRep(),
                        model.getSpecRep());
                view.repaint();
            }
            else if(mode.equals(Mode.StartRepeatMode)) {
                int xBeat = getBeat(x);
                Sign temp = new Sign(xBeat, 1);
                model.addStartRep(temp);
                Collections.sort(model.getStartRep());
                view.setAll(model.getStartSheetMusic(),
                        model.getEndSheetMusic(),
                        model.length(),
                        model.getLowest(),
                        model.getHighest(),
                        model.getTempo(),
                        model.getStartRep(),
                        model.getContRep(),
                        model.getSpecRep());
                view.repaint();
                mode = Mode.Normal;
            }
            else if(mode.equals(Mode.ContRepeatMode)) {
                int xBeat = getBeat(x);
                Sign temp = new Sign(xBeat, 0);
                model.addContRep(temp);
                Collections.sort(model.getContRep());
                view.setAll(model.getStartSheetMusic(),
                        model.getEndSheetMusic(),
                        model.length(),
                        model.getLowest(),
                        model.getHighest(),
                        model.getTempo(),
                        model.getStartRep(),
                        model.getContRep(),
                        model.getSpecRep());
                view.repaint();
                mode = Mode.Normal;
            }
            else if(mode.equals(Mode.SpecRepeatMode)) {
                int xBeat = getBeat(x);
                Sign temp = new Sign(xBeat, 0);
                model.addSpecRep(temp);
                Collections.sort(model.getSpecRep());
                view.setAll(model.getStartSheetMusic(),
                        model.getEndSheetMusic(),
                        model.length(),
                        model.getLowest(),
                        model.getHighest(),
                        model.getTempo(),
                        model.getStartRep(),
                        model.getContRep(),
                        model.getSpecRep());
                view.repaint();
                mode = Mode.Normal;
            }
        }
    }

    /**
     * Sets up a {@code HashMap} of {@code Integer objects}, representing
     * the number associated with a pressed mouse button, and a
     * {@code MouseRunnable object} containing actions performed if particular
     * {@code MouseEvents} are triggered.
     */
    private void confiqureMouseMapListener() {
        // initialize the map for the mouse handler
        HashMap<Integer, MouseRunnable> map = new HashMap<>();

        // put the runnable object in the map
        map.put(1, new MouseRunnable());

        MouseHandler mouseHandler = new MouseHandler(map);

        this.view.addMouseListener(mouseHandler);

    }

    /**
     * Acts as a runnable that sets the {@code HorizontalScrollBar} to its
     * correct value if a repetition has been used and the currently played
     * part of the song is not visible.
     */
    private class adjustScroll {
        public void run(int diff) {
            JScrollPane pane = view.getScrollPane();
            JScrollBar bar = pane.getHorizontalScrollBar();
            bar.setValue(getXFromBeat(diff));
            view.repaint();
        }
    }

    /**
     * Returns a note of the appropriate {@code Pitch} and length
     * depending on the coordinates of the mouse in the GUI when
     * a new Note request was issued.
     * @param x the x-coordinate of the position of the mouse
     * @param y the y-coordinate of the position of the mouse
     * @return a new Note of the appropriate {@code Pitch} and length
     * for the current position of the mouse in the GUI.
     */
    public Note getNote(int x, int y) {
        int n = y / ConcreteGuiViewPanel.sqr - 1;
        int temp = x / ConcreteGuiViewPanel.sqr - 2;
        return new Note(temp, temp+1, 1, getPitch(y), 75);
    }

    /**
     * Returns the beat of a note that would be generated at the place where
     * the mouse is positioned.
     * @param x the x-coordinate of the mouse
     * @return the beat of a new note that will be at the place where the mouse
     * is positioned currently.
     */
    public int getBeat(int x) {
        return x / ConcreteGuiViewPanel.sqr - 2;
    }

    /**
     * Returns the x-coordinate associated with the given {@param beat}.
     * @param beat the given beat
     * @return the x-coordinate associated with the given {@param beat}
     */
    private int getXFromBeat(int beat) {
        return (beat * ConcreteGuiViewPanel.sqr + 2);
    }

    /**
     * Returns the {@code Pitch} associated with the given
     * {@param y} coordinate.
     * @param y the given y-coordinate
     * @return the {@code Pitch} associated with the given
     * {@param y} coordinate
     */
    private int getPitch(int y) {
        int n = y / ConcreteGuiViewPanel.sqr - 1;
        return this.model.getHighest().toVal() - n;
    }

    /**
     * {@code ActionListener} for the time. Its method actionPerformed controls
     * playing the music and showing the GUI simultaneously. Its methods
     * solveStartRep and solveSpecRep allow repetition in two different ways:
     * basic repetition and varied repetition.
     */
    private class TimerAction implements ActionListener {

        private int time;
        private List<Integer> jumps;

        TimerAction(){
            this.time = 0;
            this.jumps = new ArrayList<>();
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            List<Sign> startRep = model.getStartRep();
            List<Sign> contRep = model.getContRep();
            List<Sign> specRep = model.getSpecRep();

            if(!startRep.isEmpty() && startRep.get(0).getBeat()==time) {
                this.solveStartRep(startRep, contRep);
            }
            if(!specRep.isEmpty() &&
                    specRep.get(0).getBeat()==time) {
                this.solveSpecRep(specRep, contRep);
            }
            int temp = timeEqualsAnyJump();
            if(temp>0) {
                time = time+temp;
            }

            view.playBeat(this.time);
            this.time += 1;
            AutoScroll mover = new AutoScroll();
            GoEnd end = new GoEnd();
            if (getXFromBeat(this.time) > (view.getWidth() - 20)) {
                mover.run();
            }
            if (model.length() < time - 1) {
                timer.stop();
                this.time = 0;
                mode = Mode.Normal;
                end.run();
            }

        }


        private void solveStartRep(List<Sign> startRep, List<Sign> contRep) {
            startRep.remove(0);
            if(!contRep.isEmpty() &&
                    !contRep.isEmpty() &&
                    contRep.get(0).getBeat()<time) {
                int prevTime = time;
                time = contRep.remove(0).getBeat();
                if ((prevTime - time) > 50) {
                    new adjustScroll().run(time);
                }
            }
            else {
                time = 0;
                new GoHome().run();
            }
        }

        private void solveSpecRep(List<Sign> specRep, List<Sign> contRep) {
            Sign temp3 = specRep.remove(0);
            this.jumps.add(temp3.getBeat());
            Collections.sort(this.jumps);
            if(!contRep.isEmpty() && contRep.get(0).getBeat()<time) {
                int prevTime = time;
                time = contRep.remove(0).getBeat();
                if ((prevTime - time) > 50) {
                    new adjustScroll().run(time);
                }
            }
            else {
                time = 0;
                new GoHome().run();
            }
        }

        /**
         * Returns an integer representing what jump has to be performed
         * currently. The value will be greater than zero only if the current
         * part of the song is one which a varied repeat sign requires to
         * be skipped.
         * @return an integer representing the required number of beats that
         * have to be skipped at this point in time
         */
        private int timeEqualsAnyJump() {
            for(Integer beat : this.jumps) {
                if(this.time==beat-4 ||
                        ((this.time > beat - 4) && this.time<=beat)) {
                    return beat-time;
                }
            }

            return 0;
        }

    }

    /**
     * Method used only for testing.
     */
    public void play0() {
        timer.getActionListeners()[0].actionPerformed(null);
    }

}