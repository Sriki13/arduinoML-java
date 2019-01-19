package fr.unice.polytech.builder.exception;

public class NoSuchStateException extends RuntimeException {

    public NoSuchStateException(String name) {
        super("No state with the name " + name + " exists.");
    }

}
