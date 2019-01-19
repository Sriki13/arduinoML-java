package fr.unice.polytech.gen;

import fr.unice.polytech.model.*;

public abstract class Visitor {

    public abstract void visit(Action action);

    public abstract void visit(Actuator actuator);

    public abstract void visit(Sensor sensor);

    public abstract void visit(App app);

    public abstract void visit(Condition condition);

    public abstract void visit(State state);

    public abstract void visit(Transition transition);

    public abstract void visit(DelayedTransition transition);

}
