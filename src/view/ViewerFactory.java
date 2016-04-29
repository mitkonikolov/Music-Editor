package view;//package cs3500.music.view;

/**
* Created by Mitko on 3/20/2016.
*/

public class ViewerFactory {

    public IView makeView(String type) {
        if (type == "console") {
            return new ConsoleViewer();
        }
        else if (type == "gui") {
            return new GuiViewFrame();
        }
        else if (type == "midi") {
            return new MidiViewTimerImpl();
        }
        else if (type == "composite") {
            return new CompositeView();
        }
        else {
            throw new IllegalArgumentException("invalid viewer type");
        }
    }


}

