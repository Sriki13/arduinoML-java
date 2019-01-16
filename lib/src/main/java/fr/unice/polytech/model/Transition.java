package fr.unice.polytech.model;

import java.util.ArrayList;
import java.util.List;

public class Transition {

    private List<Condition> conditions;
    private State next;
    private int delay = 0;

    public Transition() {
        conditions = new ArrayList<>();
    }

    public void setNext(State next) {
        this.next = next;
    }

    public void setDelay(int delay) {
        this.delay = delay;
    }

    public void addCondition(Condition condition) {
        conditions.add(condition);
    }
}
