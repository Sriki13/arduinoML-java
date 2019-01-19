package fr.unice.polytech.model;

import fr.unice.polytech.gen.Visitable;
import fr.unice.polytech.gen.Visitor;

import java.util.ArrayList;
import java.util.List;

public class State extends NamedElement implements Visitable {

    private List<Action> actions;
    private List<Transition> transitions;

    public State(String name) {
        super(name);
        actions = new ArrayList<>();
        transitions = new ArrayList<>();
    }

    public List<Transition> getTransitions() {
        return transitions;
    }

    public List<Action> getActions() {
        return actions;
    }

    public void addAction(Action action) {
        actions.add(action);
    }

    public void addTransition(Transition transition) {
        transitions.add(transition);
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

}
