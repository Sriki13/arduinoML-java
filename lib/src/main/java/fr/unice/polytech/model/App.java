package fr.unice.polytech.model;

import java.util.ArrayList;
import java.util.List;

public class App extends NamedElement {

    private State initial;

    private List<State> states;
    private List<Brick> bricks;

    public App(String name) {
        super(name);
        this.states = new ArrayList<>();
        this.bricks = new ArrayList<>();
    }

    public void addBrick(Brick brick) {
        bricks.add(brick);
    }

    public void addState(State state) {
        states.add(state);
    }

    public List<Brick> getBricks() {
        return bricks;
    }

    public List<State> getStates() {
        return states;
    }

    public State getInitial() {
        return initial;
    }

    public void setInitial(State initial) {
        this.initial = initial;
    }

}
