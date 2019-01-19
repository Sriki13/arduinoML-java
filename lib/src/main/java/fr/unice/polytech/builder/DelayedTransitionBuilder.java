package fr.unice.polytech.builder;

import fr.unice.polytech.model.DelayedTransition;

public class DelayedTransitionBuilder {

    private DelayedTransition transition;
    private TransitionTableBuilder parent;

    public DelayedTransitionBuilder(DelayedTransition transition, TransitionTableBuilder parent) {
        this.transition = transition;
        this.parent = parent;
    }

    public TransitionTableBuilder goTo(String name) {
        transition.setNext(parent.getState(name));
        return parent;
    }

}
