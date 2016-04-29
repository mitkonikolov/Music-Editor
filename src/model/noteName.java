package model;

/**
 * Created by Mitko on 4/2/16.
 */
public enum noteName {
    C, CS, D, DS, E, F, FS, G, GS, A, AS, B;

    public static noteName getName(int i) {
        i = i % 12;
        switch(i) {
            case 0: return C;
            case 1: return CS;
            case 2: return D;
            case 3: return DS;
            case 4: return E;
            case 5: return F;
            case 6: return FS;
            case 7: return G;
            case 8: return GS;
            case 9: return A;
            case 10: return AS;
            default: return B;
        }
    }
}
