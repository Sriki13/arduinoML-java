package fr.unice.polytech.builder.exception;

public class NoSuchBrickException extends RuntimeException {

    public NoSuchBrickException(String name, String type) {
        super("No " + type + " with name " + name + " has been defined in this application.");
    }

}
