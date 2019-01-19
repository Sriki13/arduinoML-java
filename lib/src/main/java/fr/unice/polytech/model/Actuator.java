package fr.unice.polytech.model;

import fr.unice.polytech.gen.Visitor;

public class Actuator extends Brick {

    public Actuator(String name, int pin) {
        super(name, pin);
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

}
