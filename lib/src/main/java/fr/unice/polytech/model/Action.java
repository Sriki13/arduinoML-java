package fr.unice.polytech.model;

public class Action {

    private Signal value;
    private Actuator actuator;

    public Action(Actuator actuator) {
        this.actuator = actuator;
    }

    public void setValue(Signal value) {
        this.value = value;
    }

}
