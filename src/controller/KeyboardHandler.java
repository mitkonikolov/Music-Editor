package controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;

/**
 * Created by Mitko on 4/6/16.
 */
public class KeyboardHandler implements KeyListener {

    private HashMap<Character, Runnable> keyTyped;
    private HashMap<Integer, Runnable> keyPressed, keyReleased;


    public KeyboardHandler(HashMap<Character, Runnable> keyTyped,
                            HashMap<Integer, Runnable> keyPressed,
                            HashMap<Integer, Runnable> keyReleased) {
        this.keyTyped = keyTyped;
        this.keyPressed = keyPressed;
        this.keyReleased = keyReleased;
    }

    public KeyboardHandler() {

    }

    void setKeyTypedMap(HashMap<Character, Runnable> map) {
        this.keyTyped = map;
    }

    void setKeyPressedMap(HashMap<Integer, Runnable> map) {
        this.keyPressed = map;
    }

    void setKeyReleasedMap(HashMap<Integer, Runnable> map) {
        this.keyReleased = map;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        if(this.keyTyped.containsKey(e.getKeyChar())) {
            this.keyTyped.get(e.getKeyChar()).run();
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(this.keyPressed.containsKey(e.getKeyCode())) {
            this.keyPressed.get(e.getKeyCode()).run();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if(this.keyReleased.containsKey(e.getKeyCode())) {
            this.keyReleased.get(e.getKeyCode()).run();
        }
    }
}
