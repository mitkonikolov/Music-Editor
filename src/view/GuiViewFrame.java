package view;

import model.Note;
import model.Sign;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseListener;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

/**
 * A skeleton Frame (i.e., a window) in Swing
 */
public class GuiViewFrame extends JFrame implements IView {

    private final  ConcreteGuiViewPanel displayPanel; // You may want to refine this to a subtype of JPanel
    private JScrollPane scrolls;


    public GuiViewFrame() {
        this.displayPanel = new ConcreteGuiViewPanel();
        this.displayPanel.setLayout(new FlowLayout());
        this.displayPanel.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        JScrollPane scrolls = new JScrollPane(this.displayPanel
                , ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        this.scrolls = scrolls;
        this.getContentPane().add(scrolls);
    }

    @Override
    public void initialize(){
        this.pack();
        this.displayPanel.setPreferredSize(this.getPreferredSize());
        this.setVisible(true);
    }

    @Override
    public void initializeFully() {

    }

    @Override
    public void setStartMusic(HashMap<Integer, Set<Note>> startPiece) {

        this.displayPanel.setStartMusic(startPiece);
    }

    @Override
    public void setEndMusic(HashMap<Integer, Set<Note>> endPiece) {

        this.displayPanel.setEndMusic(endPiece);
    }

    public void setAllMusic(HashMap<Integer, Set<Note>> startPiece,
                            HashMap<Integer, Set<Note>> endPiece) {
        this.displayPanel.setAllMusic(startPiece, endPiece);
    }

    @Override
    public void setLength(int length) {
        this.displayPanel.setLength(length);
    }

    @Override
    public void setHighest(Note highest) {
        this.displayPanel.setHighest(highest);
    }

    @Override
    public void setLowest(Note lowest) {
        this.displayPanel.setLowest(lowest);
    }

    @Override
    public void addMouseListener(MouseListener listener) {
        this.displayPanel.addMouseListener(listener);
    }

    private void setStartRep(List<Sign> startRep) {
        this.displayPanel.setStartRep(startRep);
    }

    private void setContRep(List<Sign> contRep) {
        this.displayPanel.setContRep(contRep);
    }

    private void setSpecRep(List<Sign> contRep) {
        this.displayPanel.setSpecRep(contRep);
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
        this.setLength(length);
        this.setLowest(lowest);
        this.setHighest(highest);
        this.setAllMusic(startPiece, endPiece); // I will need highest and lowest to be up-to date for yPos
        this.setStartRep(startRep);
        this.setContRep(contRep);
        this.setSpecRep(specRep);
    }

    @Override
    public void playBeat(int i) {
        this.displayPanel.setBeat(i);
        this.repaint();
    }

    @Override
    public void updateLine(int i) {

    }

    @Override
    public JScrollPane getScrollPane() {
        return this.scrolls;
    }

    @Override
    public Dimension getPreferredSize(){
        return this.displayPanel.getPreferredSize();
    }

    public int getWidth() {
        int res = this.scrolls.getViewport().getWidth();
        return res;
    }

    @Override
    public void update() {
        super.update(this.getGraphics());
        this.displayPanel.update(this.displayPanel.getGraphics());
    }

    @Override
    public void paint(Graphics grap) {
        super.paint(this.getGraphics());
        this.updateVertScroll();
    }

    @Override
    public void repaint() {
        super.repaint();
        this.updateVertScroll();
    }

    private void updateVertScroll() {
        this.getScrollPane().getVerticalScrollBar().update(
                this.getScrollPane().getVerticalScrollBar().getGraphics());
    }

}
