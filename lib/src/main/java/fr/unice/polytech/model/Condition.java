package fr.unice.polytech.model;

import fr.unice.polytech.gen.Visitable;
import fr.unice.polytech.gen.Visitor;

public class Condition implements Visitable {

    private Sensor sensor;
    private Signal value;

    public Condition(Sensor sensor) {
        this.sensor = sensor;
    }

    public void setValue(Signal value) {
        this.value = value;
    }

    public Sensor getSensor() {
        return sensor;
    }

    public Signal getValue() {
        return value;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

}
