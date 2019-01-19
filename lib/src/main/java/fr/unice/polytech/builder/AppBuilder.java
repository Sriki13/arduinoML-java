package fr.unice.polytech.builder;

import fr.unice.polytech.builder.exception.*;
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
        if (app.getInitial() != null) {
            throw new MultipleInitialStatesException(app.getInitial().getName(), state.getName());
        }
        app.setInitial(state);
    }

    protected void addTransition(String name, Transition transition) {
        getState(name).addTransition(transition);
    }

    public static AppBuilder application(String name) {
        checkValidName(name);
        return new AppBuilder(new App(name));
    }

    public AppBuilder brick(Brick brick) {
        app.addBrick(brick);
        return this;
    }

    private static void checkValidName(String name) {
        if (name.isEmpty()) {
            throw new IllegalArgumentException("Empty identifiers are not allowed");
        }
        if (!Character.isJavaIdentifierStart(name.charAt(0))) {
            throw new IllegalArgumentException("Invalid identifier start: " + name);
        }
        for (int i = 1; i < name.length(); i++) {
            if (!Character.isJavaIdentifierPart(name.charAt(i))) {
                throw new IllegalArgumentException("Invalid char in identifier " + name + " : " + name.charAt(i));
            }
        }
    }

    public static Sensor sensor(String name, int pin) {
        checkValidName(name);
        return new Sensor(name, pin);
    }

    public static Actuator actuator(String name, int pin) {
        checkValidName(name);
        return new Actuator(name, pin);
    }

    public StateBuilder withState(String name) {
        checkValidName(name);
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
        app.getStates().forEach(state -> {
            if (state.getTransitions().stream().filter(Transition::hasDelay).count() > 1) {
                throw new InvalidStateException("No state can contain more than one delayed" +
                        " transition: multiple found for state " + state.getName());
            }
            if (state.getTransitions().stream().anyMatch(t -> t.getConditions().isEmpty())
                    && state.getTransitions().size() > 0) {
                throw new InvalidStateException("State has multiple transitions with a transition that has no " +
                        "conditions: " + state.getName());
            }
        });
        return app;
    }

}
