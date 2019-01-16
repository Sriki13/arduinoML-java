package fr.unice.polytech.builder;

import fr.unice.polytech.model.Action;
import fr.unice.polytech.model.Signal;

public class ActionBuilder {

    private StateBuilder parent;
    private Action action;

    protected ActionBuilder(Action action, StateBuilder parent) {
        this.parent = parent;
        this.action = action;
    }

    public StateBuilder toLow() {
        this.action.setValue(Signal.LOW);
        return parent;
    }

    public StateBuilder toHigh() {
        this.action.setValue(Signal.HIGH);
        return parent;
    }

}
