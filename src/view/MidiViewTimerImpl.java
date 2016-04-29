package view;


import model.Note;
import model.Sign;

import javax.sound.midi.*;
import javax.swing.*;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.util.HashMap;
import java.util.List;
import java.util.Set;


/**
 * Created by Mitko on 4/6/2016.
 */
public class MidiViewTimerImpl implements IView {

    private final Synthesizer synth;
    private final Receiver receiver;
    private final ShortMessage message;

    private HashMap<Integer, Set<Note>> startPiece;
    private HashMap<Integer, Set<Note>> endPiece;
    private int length;
    private int tempo;

    public MidiViewTimerImpl() {

        this.startPiece = new HashMap<>();
        this.endPiece = new HashMap<>();
        this.length = 0;
        this.tempo = 0;

        Synthesizer tempSynth;
        Receiver tempReceiver;
        try {
            tempSynth = MidiSystem.getSynthesizer();
            tempReceiver = tempSynth.getReceiver();
            tempSynth.open();
        } catch (MidiUnavailableException e) {
            tempSynth = null;
            tempReceiver = null;
        }
        this.synth = tempSynth;
        this.receiver = tempReceiver;
        this.message = new ShortMessage();
    }

    public MidiViewTimerImpl(Receiver receiver, ShortMessage message) {

        this.startPiece = new HashMap<>();
        this.endPiece = new HashMap<>();
        this.length = 0;
        this.tempo = 0;

        Synthesizer tempSynth;
        try {
            tempSynth = MidiSystem.getSynthesizer();
            tempSynth.open();
        } catch (MidiUnavailableException e) {
            tempSynth = null;
        }
        this.synth = tempSynth;
        this.receiver = receiver;
        this.message = message;
    }



    @Override
    public void setStartMusic(HashMap<Integer, Set<Note>> startPiece) {
        this.startPiece = startPiece;
    }

    @Override
    public void setEndMusic(HashMap<Integer, Set<Note>> endPiece) {
        this.endPiece = endPiece;
    }

    public void setLength(int length){
        this.length = length;
    }

    public void setHighest(Note highest) {}

    public void setLowest(Note lowest) {}

    public void setTempo(int temp) {
        this.tempo = temp;
    }



    /**
     * Relevant classes and methods from the javax.sound.midi library:
     * <ul>
     *  <li>{@link MidiSystem#getSynthesizer()}</li>
     *  <li>{@link Synthesizer}
     *    <ul>
     *      <li>{@link Synthesizer#open()}</li>
     *      <li>{@link Synthesizer#getReceiver()}</li>
     *      <li>{@link Synthesizer#getChannels()}</li>
     *    </ul>
     *  </li>
     *  <li>{@link Receiver}
     *    <ul>
     *      <li>{@link Receiver#send(MidiMessage, long)}</li>
     *      <li>{@link Receiver#close()}</li>
     *    </ul>
     *  </li>
     *  <li>{@link MidiMessage}</li>
     *  <li>{@link ShortMessage}</li>
     *  <li>{@link MidiChannel}
     *    <ul>
     *      <li>{@link MidiChannel#getProgram()}</li>
     *      <li>{@link MidiChannel#programChange(int)}</li>
     *    </ul>
     *  </li>
     * </ul>
     * @see <a href="https://en.wikipedia.org/wiki/General_MIDI">
     *   https://en.wikipedia.org/wiki/General_MIDI
     *   </a>
     */

    public void playNote(int noteVal, int volume) throws InvalidMidiDataException {
        MidiMessage start = new ShortMessage(ShortMessage.NOTE_ON, 0, noteVal, volume);
        MidiMessage stop = new ShortMessage(ShortMessage.NOTE_OFF, 0, noteVal, volume);
        this.receiver.send(start, -1);
        this.receiver.send(stop, this.synth.getMicrosecondPosition() + 200000);
        try {
            Thread.sleep(1000);
        } catch (Exception e) {

        }
        this.receiver.close();
    }

    @Override
    public void playBeat(int beat){
        int tempo = this.tempo;
        int length = this.length;
        Set<Note> tempStart = this.startPiece.get(beat);
        Set<Note> tempEnd = this.endPiece.get(beat-1);

        if(tempEnd != null) {
            for(Note n : tempEnd) {
                try {
                    this.message.setMessage(ShortMessage.NOTE_OFF, 1, n.toVal(),
                            n.getVolume());
                    this.receiver.send(this.message, beat * tempo);
                }
                catch (Exception e) {}
            }
        }

        if(tempStart != null) {
            for(Note n : tempStart) {
                try {
                    this.message.setMessage(ShortMessage.NOTE_ON, 1, n.toVal(),
                            n.getVolume());
                    this.receiver.send(this.message, beat * tempo);
                }
                catch (Exception e) {}
            }

        }
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

    //@Override
    public void repaint() {

    }




    @Override
    public void initialize() {
    }

    @Override
    public void update() {

    }

    @Override
    public void initializeFully() {

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
        this.setTempo(tempo);
    }

}
