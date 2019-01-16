package fr.unice.polytech.builder;

import fr.unice.polytech.model.Condition;
import fr.unice.polytech.model.Transition;

public class TransitionBuilder {

    public Transition transition;
    public TransitionTableBuilder parent;

    protected TransitionBuilder(Transition transition, TransitionTableBuilder parent) {
        this.transition = transition;
        this.parent = parent;
    }

    public ConditionBuilder when(String name) {
        Condition condition = new Condition(parent.getSensor(name));
        transition.addCondition(condition);
        return new ConditionBuilder(condition, this);
    }

    public ConditionBuilder and(String name) {
        return when(name);
    }

    public TransitionBuilder then() {
        return this;
    }

    public TransitionBuilder after(int time) {
        transition.setDelay(time);
        return this;
    }

    public TransitionTableBuilder goTo(String name) {
        transition.setNext(parent.getState(name));
        return parent;
    }

}
