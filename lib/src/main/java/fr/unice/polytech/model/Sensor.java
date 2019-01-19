package fr.unice.polytech.model;

import fr.unice.polytech.gen.Visitor;

public class Sensor extends Brick {

    public Sensor(String name, int pin) {
        super(name, pin);
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

}
