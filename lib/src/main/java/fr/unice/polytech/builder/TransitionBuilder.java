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

    public ConditionBuilder when(String sensor) {
        Condition condition = new Condition(parent.getSensor(sensor));
        transition.addCondition(condition);
        return new ConditionBuilder(condition, this);
    }

    public ConditionBuilder and(String sensor) {
        return when(sensor);
    }

    public TransitionBuilder then() {
        return this;
    }

    public TransitionTableBuilder goTo(String state) {
        transition.setNext(parent.getState(state));
        return parent;
    }

}
