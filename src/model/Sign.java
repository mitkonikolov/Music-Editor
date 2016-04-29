package model;

import java.util.Objects;

/**
 * Created by Mitko on 4/27/16.
 */
public class Sign implements Comparable<Sign> {
    private int beat;
    private int type;

    public Sign(int beat, int type) {
        this.beat = beat;
        this.type = type;
    }

    public boolean isStartRep() {
        if(this.type==1) {
            return true;
        }

        return false;
    }

    public boolean isContRep() {
        if(this.type==0) {
            return true;
        }

        return false;
    }

    public boolean isSpecialRepeat() {
        if(this.type==-1) {
            return true;
        }

        return false;
    }

    public int getBeat() {
        return this.beat;
    }

    @Override
    public int compareTo(Sign o) {
        return this.beat - o.beat;
    }

    @Override
    public boolean equals(Object o) {
        if(! (o instanceof Sign)) {
            return false;
        }

        Sign temp = (Sign)o;
        return this.beat==temp.beat && this.type==temp.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.beat, this.type);
    }
}
