package fr.unice.polytech.builder.exception;

public class MultipleInitialStatesException extends RuntimeException {

    public MultipleInitialStatesException(String original, String newer) {
        super("The state " + original + " is already defined as original: cannot define " + newer + " as original.");
    }
}
