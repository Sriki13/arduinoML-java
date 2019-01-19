package fr.unice.polytech.model;

import fr.unice.polytech.gen.Visitable;
import fr.unice.polytech.gen.Visitor;

public class Action implements Visitable {

    private Signal value;
    private Actuator actuator;

    public Action(Actuator actuator) {
        this.actuator = actuator;
    }

    public void setValue(Signal value) {
        this.value = value;
    }

    public Signal getValue() {
        return value;
    }

    public Actuator getActuator() {
        return actuator;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
