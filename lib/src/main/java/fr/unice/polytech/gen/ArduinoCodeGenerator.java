package fr.unice.polytech.gen;

import fr.unice.polytech.model.*;

import java.util.ArrayList;
import java.util.List;

public class ArduinoCodeGenerator extends Visitor {

    private App app;
    private CodeWriter writer;
    private List<String> setupLines;

    private ArduinoCodeGenerator(App app) {
        this.app = app;
        this.setupLines = new ArrayList<>();
        this.writer = new CodeWriter();
    }

    public static ArduinoCodeGenerator generator(App app) {
        return new ArduinoCodeGenerator(app);
    }

    public String getCode() {
        visit(app);
        return writer.getCode();
    }

    private static final String APP_SETUP = "" +
            "#include <SimpleTimer.h>\n" +
            "\n" +
            "SimpleTimer timer;\n" +
            "int timerId = 0;\n\n";

    private void endBlock() {
        writer.unindent();
        writer.writeLn("}");
    }

    @Override
    public void visit(App app) {
        writer.write(APP_SETUP);
        app.getBricks().forEach(b -> b.accept(this));
        writer.writeLn();
        writer.writeLn("void setup() {");
        writer.indent();
        setupLines.forEach(line -> writer.writeLn(line));
        endBlock();
        writer.writeLn();
        app.getStates().forEach(this::visit);
        writer.writeLn("void loop() {");
        writer.indent();
        writer.writeLn(app.getInitial().getName() + "();");
        endBlock();
    }


    @Override
    public void visit(Actuator actuator) {
        writer.writeLn("int " + actuator.getName() + " = " + actuator.getPin() + ";");
        setupLines.add("pinMode(" + actuator.getName() + ", OUTPUT);");
    }

    @Override
    public void visit(Sensor sensor) {
        writer.writeLn("int " + sensor.getName() + "  = " + sensor.getPin() + ";");
        setupLines.add("pinMode(" + sensor.getName() + ", INPUT);");
    }


    private static final String STATE_SETUP = "timer.deleteTimer(timerId);";
    private int transitionCount = 0;

    @Override
    public void visit(State state) {
        this.transitionCount = 0;
        writer.writeLn("void " + state.getName() + "() {");
        writer.indent();
        writer.writeLn(STATE_SETUP);
        state.getTransitions().stream()
                .filter(Transition::hasDelay)
                .forEach(t -> t.accept(this));
        writer.writeLn("while(1) {");
        writer.indent();
        writer.writeLn("delay(100);");
        writer.writeLn("timer.run();");
        state.getActions().forEach(this::visit);
        state.getTransitions().stream()
                .filter(t -> !t.hasDelay())
                .forEach(t -> t.accept(this));
        endBlock();
        endBlock();
        writer.writeLn();
    }

    @Override
    public void visit(Action action) {
        writer.writeLn("digitalWrite(" + action.getActuator().getName() + ", " + action.getValue() + ");");
    }


    @Override
    public void visit(DelayedTransition transition) {
        writer.writeLn("timerId = timer.setTimeout(" + transition.getDelay() + ", " + transition.getNext().getName() + ");");
    }

    @Override
    public void visit(Transition transition) {
        if (transition.getConditions().isEmpty()) {
            writer.writeLn(transition.getNext().getName() + "()");
            return;
        }
        if (transitionCount == 0) {
            writer.startLn("if (");
        } else {
            writer.startLn("else if (");
        }
        for (int i = 0; i < transition.getConditions().size(); i++) {
            if (i != 0) {
                writer.write(" && ");
            }
            transition.getConditions().get(i).accept(this);

        }
        writer.write(") {\n");
        writer.indent();
        writer.writeLn(transition.getNext().getName() + "();");
        endBlock();
        transitionCount++;
    }

    @Override
    public void visit(Condition condition) {
        writer.write("digitalRead(" + condition.getSensor().getName()+ ") == " + condition.getValue());
    }

}
