package fr.unice.polytech.model;

import java.util.ArrayList;
import java.util.List;

public class State extends NamedElement {

    private List<Action> actions;
    private List<Transition> transitions;

    public State(String name) {
        super(name);
        actions = new ArrayList<>();
        transitions = new ArrayList<>();
    }

    public void addAction(Action action) {
        actions.add(action);
    }

    public void addTransition(Transition transition) {
        transitions.add(transition);
    }

}
