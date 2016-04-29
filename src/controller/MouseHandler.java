package controller;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashMap;

/**
 * Created by Mitko on 4/7/16.
 */
public class MouseHandler implements MouseListener{
    private HashMap<Integer, Controller.MouseRunnable> mappings;

    public MouseHandler(HashMap<Integer, Controller.MouseRunnable> map) {
        this.mappings = map;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getButton()==1) {
            this.mappings.get(1).run(e.getX(), e.getY());
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
