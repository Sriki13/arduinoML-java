package fr.unice.polytech.builder;

import fr.unice.polytech.model.Action;
import fr.unice.polytech.model.State;

public class StateBuilder {

    private AppBuilder parent;
    private State state;

    protected StateBuilder(State state, AppBuilder parent) {
        this.state = state;
        this.parent = parent;
    }

    public ActionBuilder set(String actuator) {
        Action action = new Action(parent.getActuator(actuator));
        state.addAction(action);
        return new ActionBuilder(action, this);
    }

    public StateBuilder initial() {
        parent.setInitial(this.state);
        return this;
    }

    public AppBuilder endState() {
        return this.parent;
    }

}
