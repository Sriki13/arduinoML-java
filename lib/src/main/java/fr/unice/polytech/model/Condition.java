package fr.unice.polytech.model;

public class Condition {

    private Sensor sensor;
    private Signal value;

    public Condition(Sensor sensor) {
        this.sensor = sensor;
    }

    public void setValue(Signal value) {
        this.value = value;
    }
}
