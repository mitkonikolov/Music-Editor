package model;

import java.util.HashMap;
import java.util.List;
import java.util.Set;

/**
 * Created by Mitko on 2/29/2016.
 */
public interface IModel {

    /** 
     * Adds the given {@code Note} {@param n} to the musical piece. 
     * @param n the given {@code Note} 
     */
    void add(Note n);

    /** 
     * Removes the given {@code Note} {@param n} from the musical piece. 
     * @param n the given {@code Note} 
     */
    void remove(Note n);

    /** 
     * Edits the {@code Note} {@param oldNote} to the 
     * {@code Note} {@param newNote} 
     * @param oldNote the {@code Note} to be edited 
     * @param newNote the desired resultant {@code Note} 
     */
    void edit(Note oldNote, Note newNote);

    /**
     * Returns a {@code HashMap} containing information about all {@code Note}
     * objects starting at different beats.
     * @return a {@code HashMap} containing information about all {@code Note}
     * objects starting at different beats
     */
    HashMap<Integer, Set<Note>> getStartSheetMusic();

    /**
     * Returns a {@code HashMap} containing information about all {@code Note}
     * objects ending at different beats.
     * @return a {@code HashMap} containing information about all {@code Note}
     * objects ending at different beats
     */
    HashMap<Integer, Set<Note>> getEndSheetMusic();

    /**
     * Returns the length of the musical piece.
     *
     * @return the number of the last beat present in the musical piece
     */
    int length();

    /**
     * Returns the {@code Note} of lowest octave and {@code Pitch} in
     * the musical piece.
     * @return the lowest {@code Note} in the musical piece
     */
    Note getLowest();

    /**
     * Returns the {@code Note} of highest octave and {@code Pitch} in
     * the musical piece.
     * @return the highest {@code Note} in the musical piece
     */
    Note getHighest();

    /**
     * Returns the model's tempo.
     *
     * @return the model's tempo
     */
    int getTempo();

    /**
     * Sets the model's tempo to the given number {@param t}.
     *
     * @param t the given number
     */
    void setTempo(int t);

    /**
     * Returns a {@code Note} object of the given {@code Pitch} {@param pitch}
     * starting at the given beat {@param start}.
     *
     * @param start the number of the beat where a {@code Note} starts
     * @param pitch the given {@code Pitch}
     * @return a {@code Note} starting at the given beat {@param start} and of
     * {@code Pitch} {@param pitch}
     */
    Note getNoteStartingAt(int start, int pitch);

    /**
     * Adds a basic repetition {@param s} to the value of the field in
     * the model representing all basic repetitions in the piece.
     *
     * @param s the {@code Sign}, representing a repetition, to be added
     */
    void addStartRep(Sign s);

    /**
     * Adds an inverted repetition {@param s} to the inverted repetitions
     * in the music piece.
     *
     * @param s the {@code Sign}, representing a repetition, to be added
     */
    void addContRep(Sign s);

    /**
     * Adds a varied repetition {@param s} to the inverted repetitions in
     * the music piece.
     *
     * @param s the {@code Sign}, representing a repetition, to be added
     */
    void addSpecRep(Sign s);

    /**
     * Returns a {@code List} of all basic repetitions in the music piece.
     *
     * @return a {@code List} of basic repetitions
     */
    List<Sign> getStartRep();

    /**
     * Returns a {@code List} of all inverted repetitions in the music piece.
     *
     * @return a {@code List} of inverted repetitions
     */
    List<Sign> getContRep();

    /**
     * Returns a {@code List} of all varied repetitions in the music piece.
     *
     * @return a {@code List} of varied repetitions
     */
    List<Sign> getSpecRep();

}
