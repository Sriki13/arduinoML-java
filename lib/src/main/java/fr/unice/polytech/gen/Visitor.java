package fr.unice.polytech.gen;

import fr.unice.polytech.model.*;

public abstract class Visitor {

    abstract void visit(Action action);

    abstract void visit(Actuator actuator);

    abstract void visit(App app);

    abstract void visit(Condition condition);

    abstract void visit(Sensor sensor);

    abstract void visit(State state);

    abstract void visit(Transition transition);

}
