package fr.unice.polytech.builder;

import fr.unice.polytech.builder.exception.NoInitialStateException;
import fr.unice.polytech.builder.exception.NoSuchBrickException;
import fr.unice.polytech.builder.exception.NoSuchStateException;
import fr.unice.polytech.model.*;

public class AppBuilder {

    private App app;

    private AppBuilder(App app) {
        this.app = app;
    }

    protected Actuator getActuator(String name) {
        for (Brick brick : app.getBricks()) {
            if (brick.getName().equals(name) && brick instanceof Actuator) {
                return (Actuator) brick;
            }
        }
        throw new NoSuchBrickException(name, "actuator");
    }

    protected Sensor getSensor(String name) {
        for (Brick brick : app.getBricks()) {
            if (brick.getName().equals(name) && brick instanceof Sensor) {
                return (Sensor) brick;
            }
        }
        throw new NoSuchBrickException(name, "sensor");
    }

    protected State getState(String name) {
        for (State state : app.getStates()) {
            if (state.getName().equals(name)) {
                return state;
            }
        }
        throw new NoSuchStateException(name);
    }

    protected void setInital(State state) {
        app.setInitial(state);
    }

    protected void addTransition(String name, Transition transition) {
        getState(name).addTransition(transition);
    }

    public static AppBuilder application(String name) {
        return new AppBuilder(new App(name));
    }

    public AppBuilder brick(Brick brick) {
        app.addBrick(brick);
        return this;
    }

    public static Sensor sensor(String name, int pin) {
        return new Sensor(name, pin);
    }

    public static Actuator actuator(String name, int pin) {
        return new Actuator(name, pin);
    }

    public StateBuilder withState(String name) {
        State state = new State(name);
        app.addState(state);
        return new StateBuilder(state, this);
    }

    public TransitionTableBuilder transitions() {
        return new TransitionTableBuilder(this);
    }

    public App build() {
        if (app.getInitial() == null) {
            throw new NoInitialStateException();
        }
        return app;
    }

}
