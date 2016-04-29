package mocks;

import javax.sound.midi.MidiMessage;
import javax.sound.midi.Receiver;

/**
 * Created by Mitko on 3/21/2016.
 */
public class ReceiverMock implements Receiver {

    public String output;

    public ReceiverMock() {
        output = "";
    }

    @Override
    public void send(MidiMessage message, long timeStamp) {
        MessageMock mock = (MessageMock)message;
        output += mock.output + " at time " + timeStamp + "\n";
    }

    @Override
    public void close() {

    }
}
