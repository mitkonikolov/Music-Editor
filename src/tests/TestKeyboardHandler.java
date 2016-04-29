package tests;

import controller.KeyboardHandler;
import org.junit.Test;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.HashMap;

import static org.junit.Assert.assertEquals;

/**
 * Created by Mitko on 4/6/2016.
 */
public class TestKeyboardHandler {

    private HashMap<Character, Runnable> keyTyped;
    private HashMap<Integer, Runnable> keyPressed;
    private HashMap<Integer, Runnable> keyReleased;
    private String output;
    private KeyboardHandler handler;

    private KeyEvent aKey = new KeyEvent(new Component() {    }, 0, 0, 0, 30, 'a', 0);
    private KeyEvent spaceKey = new KeyEvent(new Component() {    }, 0, 0, 0, 57, ' ', 0);
    private KeyEvent qKey = new KeyEvent(new Component() {    }, 0, 0, 0, 16, 'q', 0);

    public void initialise() {
        output = "Key Handler Test:";
        keyTyped = new HashMap<Character, Runnable>();
        keyTyped.put('a', new aTyped ());
        keyTyped.put(' ', new spaceTyped());
        keyPressed = new HashMap<Integer, Runnable>();
        keyPressed.put(30, new aPressed());
        keyPressed.put(57, new spacePressed());
        keyReleased = new HashMap<Integer, Runnable>();
        keyReleased.put(30, new aReleased());
        keyReleased.put(57, new spaceReleased());
        handler = new KeyboardHandler(keyTyped, keyPressed, keyReleased);
    }

    @Test
    public void testKeyBoardHandler() {
        initialise();
        handler.keyTyped(aKey);
        handler.keyPressed(aKey);
        handler.keyReleased(aKey);
        handler.keyTyped(spaceKey);
        handler.keyPressed(spaceKey);
        handler.keyReleased(spaceKey);
        handler.keyTyped(qKey);
        handler.keyPressed(qKey);
        handler.keyReleased(qKey);
        assertEquals("Key Handler Test:\n" +
                "a key typed\n" +
                "a key pressed\n" +
                "a key released\n" +
                "space key typed\n" +
                "space key pressed\n" +
                "space key released", this.output);

    }

    public class aTyped implements Runnable {
        @Override
        public void run() {
            output += "\na key typed";
        }
    }

    public class aPressed implements Runnable {
        @Override
        public void run() {
            output += "\na key pressed";
        }
    }

    public class aReleased implements Runnable {
        @Override
        public void run() {
            output += "\na key released";
        }
    }

    public class spaceTyped implements Runnable {
        @Override
        public void run() {
            output += "\nspace key typed";
        }
    }

    public class spacePressed implements Runnable {
        @Override
        public void run() {
            output += "\nspace key pressed";
        }
    }

    public class spaceReleased implements Runnable {
        @Override
        public void run() {
            output += "\nspace key released";
        }
    }

}
