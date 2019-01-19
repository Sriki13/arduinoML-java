package fr.unice.polytech.model;

import fr.unice.polytech.gen.Visitable;
import fr.unice.polytech.gen.Visitor;

public class DelayedTransition extends Transition implements Visitable {

    private int delay = 0;

    public void setDelay(int delay) {
        this.delay = delay;
    }

    public int getDelay() {
        return delay;
    }

    @Override
    public boolean hasDelay() {
        return true;
    }

    @Override
    public void addCondition(Condition condition) {
        throw new UnsupportedOperationException("Timed transitions cannot have conditions");
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

}
