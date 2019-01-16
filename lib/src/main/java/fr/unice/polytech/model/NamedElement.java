package fr.unice.polytech.model;

public abstract class NamedElement {

    protected String name;

    public NamedElement(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
