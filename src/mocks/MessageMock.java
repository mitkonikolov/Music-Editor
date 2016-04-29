package mocks;

import javax.sound.midi.ShortMessage;

/**
 * Created by Mitko on 3/21/2016.
 */

public class MessageMock extends ShortMessage {

    public String output;

    public MessageMock() {
        output = "";
    }

    @Override
    public void setMessage(int command, int data1, int data2) {
        if (command == 144) {
            output =  "note begun at pitch " + data1 + " volume " + data2;
        }
        else {
            output =  "note ended at pitch " + data1 + " volume " + data2;
        }
    }

}
