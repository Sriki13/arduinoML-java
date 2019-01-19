package fr.unice.polytech.builder.exception;

public class NoInitialStateException extends RuntimeException {

    public NoInitialStateException() {
        super("No state was defined as initial.");
    }
}
