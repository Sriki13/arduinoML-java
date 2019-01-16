package fr.unice.polytech.builder;

import fr.unice.polytech.model.Condition;
import fr.unice.polytech.model.Signal;

public class ConditionBuilder {

    private Condition condition;
    private TransitionBuilder parent;

    public ConditionBuilder(Condition condition, TransitionBuilder parent) {
        this.condition = condition;
        this.parent = parent;
    }

    public TransitionBuilder isHigh() {
        condition.setValue(Signal.HIGH);
        return parent;
    }

    public TransitionBuilder isLow() {
        condition.setValue(Signal.LOW);
        return parent;
    }

}
