package fr.unice.polytech.model;

public class Brick extends NamedElement {

    private int pin;

    public Brick(String name, int pin) {
        super(name);
        this.pin = pin;
    }
}
