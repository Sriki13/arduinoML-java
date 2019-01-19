package fr.unice.polytech.model;

import fr.unice.polytech.gen.Visitable;
import fr.unice.polytech.gen.Visitor;

import java.util.ArrayList;
import java.util.List;

public class Transition implements Visitable {

    private List<Condition> conditions;
    private State next;

    public Transition() {
        conditions = new ArrayList<>();
    }

    public void setNext(State next) {
        this.next = next;
    }

    public List<Condition> getConditions() {
        return conditions;
    }

    public State getNext() {
        return next;
    }

    public boolean hasDelay() {
        return false;
    }

    public void addCondition(Condition condition) {
        conditions.add(condition);
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

}
