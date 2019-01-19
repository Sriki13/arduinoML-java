package fr.unice.polytech.model;

import fr.unice.polytech.gen.Visitable;
import fr.unice.polytech.gen.Visitor;

public abstract class Brick extends NamedElement implements Visitable {

    private int pin;

    public Brick(String name, int pin) {
        super(name);
        this.pin = pin;
    }

    public int getPin() {
        return pin;
    }

    @Override
    public abstract void accept(Visitor visitor);

}
