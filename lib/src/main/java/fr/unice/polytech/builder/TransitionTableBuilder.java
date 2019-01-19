package fr.unice.polytech.builder;

import fr.unice.polytech.model.DelayedTransition;
import fr.unice.polytech.model.Sensor;
import fr.unice.polytech.model.State;
import fr.unice.polytech.model.Transition;

public class TransitionTableBuilder {

    private AppBuilder parent;

    protected TransitionTableBuilder(AppBuilder parent) {
        this.parent = parent;
    }

    protected State getState(String name) {
        return parent.getState(name);
    }

    protected Sensor getSensor(String name) {
        return parent.getSensor(name);
    }

    public TransitionBuilder from(String state) {
        Transition transition = new Transition();
        parent.addTransition(state, transition);
        return new TransitionBuilder(transition, this);
    }

    public DelayedTransitionBuilder after(String state, int delay) {
        DelayedTransition transition = new DelayedTransition();
        transition.setDelay(delay);
        parent.addTransition(state, transition);
        return new DelayedTransitionBuilder(transition, this);
    }

    public AppBuilder endTransitions() {
        return parent;
    }

}
