package model;

import java.util.Objects;

/**
 * Created by Mitko on 2/26/2016.
 */
public class Note implements Comparable<Note> {
    private noteName name;
    private int octave;
    private int startBeat;
    private int endBeat;
    private int instrument;
    private int volume;


    public Note(noteName name, int octave, int startBeat, int endBeat,
                int instrument, int volume) {
        this.name = name;
        this.octave = octave;
        this.startBeat = startBeat;
        this.endBeat = endBeat;
        this.instrument = instrument;
        this.volume = volume;
    }

    public Note(int startBeat, int endBeat, int instrument, int pitch, int volume) {
        int n = pitch % 12;
        if (n == 0) {this.name = noteName.C;}
        else if (n == 1) {this.name = noteName.CS;}
        else if (n == 2) {this.name = noteName.D;}
        else if (n == 3) {this.name = noteName.DS;}
        else if (n == 4) {this.name = noteName.E;}
        else if (n == 5) {this.name = noteName.F;}
        else if (n == 6) {this.name = noteName.FS;}
        else if (n == 7) {this.name = noteName.G;}
        else if (n == 8) {this.name = noteName.GS;}
        else if (n == 9) {this.name = noteName.A;}
        else if (n == 10) {this.name = noteName.AS;}
        else {this.name = noteName.B;}

        this.octave = pitch / 12;
        this.startBeat = startBeat;
        this.endBeat = endBeat;
        this.instrument = instrument;
        this.volume = volume;
    }

    public Note(int pitch) {
        int n = pitch % 12;
        if (n == 0) {this.name = noteName.C;}
        else if (n == 1) {this.name = noteName.CS;}
        else if (n == 2) {this.name = noteName.D;}
        else if (n == 3) {this.name = noteName.DS;}
        else if (n == 4) {this.name = noteName.E;}
        else if (n == 5) {this.name = noteName.F;}
        else if (n == 6) {this.name = noteName.FS;}
        else if (n == 7) {this.name = noteName.G;}
        else if (n == 8) {this.name = noteName.GS;}
        else if (n == 9) {this.name = noteName.A;}
        else if (n == 10) {this.name = noteName.AS;}
        else {this.name = noteName.B;}

        this.octave = pitch / 12;
        this.startBeat = 0;
        this.endBeat = 0;
        this.instrument = 0;
        this.volume = 0;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Note)) {
            return false;
        }

        Note temp = (Note) o;

        return  this.name.equals(temp.name) &&
                this.octave==temp.octave &&
                this.instrument == temp.instrument &&
                this.volume == temp.volume;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, octave, instrument, volume);
    }

    /**
     * Prints the note as a string
     * @return the note as a string
     */
    public String print() {
        String answer = "";
        if (this.name == noteName.C) {answer += "C";}
        else if (this.name == noteName.CS) {answer += "C#";}
        else if (this.name == noteName.D) {answer += "D";}
        else if (this.name == noteName.DS) {answer += "D#";}
        else if (this.name == noteName.E) {answer += "E";}
        else if (this.name == noteName.F) {answer += "F";}
        else if (this.name == noteName.FS) {answer += "F#";}
        else if (this.name == noteName.G) {answer += "G";}
        else if (this.name == noteName.GS) {answer += "G#";}
        else if (this.name == noteName.A) { answer += "A";}
        else if (this.name == noteName.AS) {answer += "A#";}
        else if (this.name == noteName.B) {answer += "B";}
        answer += this.octave;
        return answer;
    }

    /**
     * Converts the note to an integer value
     * @return the note's integer value
     */
    public int toVal() {
        int answer = 0;
        if (this.name == noteName.C) {answer = 0;}
        else if (this.name == noteName.CS) {answer = 1;}
        else if (this.name == noteName.D) {answer = 2;}
        else if (this.name == noteName.DS) {answer = 3;}
        else if (this.name == noteName.E) {answer = 4;}
        else if (this.name == noteName.F) {answer = 5;}
        else if (this.name == noteName.FS) {answer = 6;}
        else if (this.name == noteName.G) {answer = 7;}
        else if (this.name == noteName.GS) {answer = 8;}
        else if (this.name == noteName.A) {answer = 9;}
        else if (this.name == noteName.AS) {answer = 10;}
        else if (this.name == noteName.B) {answer = 11;}
        answer = answer + (this.octave * 12);
        return answer;
    }

    public int getStartBeat() {
        return this.startBeat;
    }

    public int getEndBeat() {
        return this.endBeat;
    }


    @Override
    public int compareTo(Note o) {
        int curr = this.toVal();
        int given = o.toVal();
        return curr-given;
    }

    public int getVolume() {
        return this.volume;
    }

    public int getInstrument() {
        return this.instrument;
    }
}


