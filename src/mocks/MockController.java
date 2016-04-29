package mocks;

import controller.Controller;
import model.IModel;
import view.IView;

/**
 * Created by Mitko on 4/6/2016.
 */
public class MockController extends Controller {

    public String output;
    public IModel model;
    public IView view;

    public MockController(IModel model, IView view) {
        super(model, view);
        this.model = model;
        this.view = view;
        output = "controller created";
    }

}
