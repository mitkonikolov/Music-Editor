package view;

import model.Note;
import model.Sign;
import model.noteName;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ConcreteGuiViewPanel extends JPanel{

    public static final int sqr = 20;
    public final Color main = Color.black;
    public final Color highlight = Color.green;

    private HashMap<Integer, Set<Note>> startPiece;
    private HashMap<Integer, Set<Note>> endPiece;
    private HashMap<Integer, Integer> yPos;
    private int length;
    private Note lowest;
    private Note highest;
    private int beat;
    private List<Sign> contRep;
    private List<Sign> startRep;
    private List<Sign> specRep;


    public ConcreteGuiViewPanel() {}

    public void setStartMusic(HashMap<Integer, Set<Note>> startPiece) {
        this.startPiece = startPiece;
    }

    public void setEndMusic(HashMap<Integer, Set<Note>> endPiece) {
        this.endPiece = endPiece;
    }

    void setAllMusic(HashMap<Integer, Set<Note>> startPiece,
                     HashMap<Integer, Set<Note>> endPiece) {
        this.setStartMusic(startPiece);
        this.setEndMusic(endPiece);
        this.yPos = new HashMap<>();
        for(int i=this.highest.toVal(); i>=this.lowest.toVal(); i--) {
            yPos.put(i, sqr * (this.highest.toVal() - i + 1));
        }
    }

    public void setLength(int length) {
        this.length = length;
    }

    public void setHighest(Note highest) {
        this.highest = highest;
    }

    public void setLowest(Note lowest) {
        this.lowest = lowest;
    }

    public void setBeat(int beat) {
        this.beat = beat;
    }

    public void paintBackgrount(Graphics2D g2d) {
        int length = this.length;
        int n = 0;
        Set<Note> currPlaying = new HashSet<>();
        //paints numbers
        while (n <= length + 1) {
            if (n % 16 == 0) {
                g2d.setColor(main);
                g2d.drawString("" + n, sqr * (n + 2) + 5, sqr - 5);
            }
            n++;
       }

        // paints the grid
        noteName temp = noteName.C;
        for (int l = 0; l <= length; l += 4) {
            for (int i = this.highest.toVal();
                 i >= this.lowest.toVal(); i--) {
                g2d.setColor(main);
                g2d.drawRect(
                        sqr * (l + 2),
                        sqr * (this.highest.toVal() - i + 1),
                        sqr * 4,
                        sqr);
                // paints the line separating ocataves
                if (temp.getName(i) == noteName.B) {
                    g2d.fillRect(
                            sqr * (l + 2),
                            sqr * (this.highest.toVal() - i + 1),
                            sqr * 4,
                            3
                    );
                }
            }
        }

        // paints the note names
        n = this.highest.toVal();
        int n0 = 0;
        while (n >= this.lowest.toVal()) {
            g2d.setColor(main);
            g2d.drawString(new Note(n).print(), 5, sqr * (n0 + 2) - (sqr / 4));
            n--;
            n0++;
        }

    }

    public void drawBeat(Graphics2D g2d) {
        g2d.setColor(Color.RED);
        g2d.fillRect((this.beat + 2) * sqr, sqr,
                3,
                (this.highest.toVal() - this.lowest.toVal() + 1) * sqr);

        // paint the start-repeat signs
        for(Sign s : this.startRep) {
            g2d.setColor(new Color(0, 60, 200));
            g2d.drawLine((s.getBeat() + 2) * sqr, sqr, (s.getBeat() + 2) * sqr,
                    (this.highest.toVal() - this.lowest.toVal() + 2) * sqr);
        }

        // paint the continue-repeat signs
        for(Sign s : this.contRep) {
            g2d.setColor(new Color(190, 50, 40));
            g2d.drawLine((s.getBeat() + 2) * sqr, sqr, (s.getBeat() + 2) * sqr,
                    (this.highest.toVal() - this.lowest.toVal() + 2) * sqr);
        }

        // paint the special-repeat signs
        for(Sign s : this.specRep) {
            g2d.setColor(new Color(230, 180, 120));
            g2d.drawLine((s.getBeat() + 2) * sqr, sqr, (s.getBeat() + 2) * sqr,
                    (this.highest.toVal() - this.lowest.toVal() + 2) * sqr);
        }
    }

    public void paintNotes(Graphics2D g2d) {
        int length = this.length;
        g2d.setColor(main);

        Set<Note> currPlaying = new HashSet<>();
        for (int l = 0; l <= length; l++) {

            Set<Note> tempStart = this.startPiece.get(l);
            Set<Note> tempEnd = this.endPiece.get(l);
            if(currPlaying==null) {
                currPlaying.addAll(this.startPiece.get(l));
            }
            else if(tempStart!=null){
                currPlaying.addAll(tempStart);
            }

            if(currPlaying.size()!= 0) {
                for (Note note : currPlaying) {
                    if(tempStart!=null && tempStart.contains(note)) {
                        g2d.setColor(main);
                        g2d.fillRect(sqr * (l + 2),
                                this.yPos.get(note.toVal()), sqr + 1, sqr + 1);
                    }
                    else {
                        g2d.setColor(highlight);
                        g2d.fillRect(sqr * (l + 2),
                                this.yPos.get(note.toVal()), sqr + 1, sqr + 1);
                    }
                }
            }

            if(tempEnd!=null) {
                for(Note note : tempEnd) {
                    currPlaying.remove(note);
                }
            }

        }

    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        paintNotes(g2d);
        paintBackgrount(g2d);
        drawBeat(g2d);
    }


    @Override
    public Dimension getPreferredSize() {
        return new Dimension(
                (this.length + 8) * ConcreteGuiViewPanel.sqr,
                (this.highest.toVal() - this.lowest.toVal() + 5) * ConcreteGuiViewPanel.sqr + 30);

    }

    public void setStartRep(List<Sign> startRep) {
        this.startRep = startRep;
    }

    public void setContRep(List<Sign> contRep) {
        this.contRep = contRep;
    }

    public void setSpecRep(List<Sign> specRep) {
        this.specRep = specRep;
    }

}