package fr.unice.polytech.example.scenarios;

import fr.unice.polytech.model.App;

import static fr.unice.polytech.builder.AppBuilder.actuator;
import static fr.unice.polytech.builder.AppBuilder.application;
import static fr.unice.polytech.builder.AppBuilder.sensor;

public class TemporalTransition implements Scenario {

    @Override
    public App getApp() {
        return application("stateBasedAlarm")
                    .brick(sensor("button", 9))
                    .brick(actuator("led", 10))
                .withState("on")
                .   set("led").toHigh()
                .endState()
                .withState("off").initial()
                    .set("led").toLow()
                .endState()
                .transitions()
                    .after("on", 800).goTo("off")
                    .from("off").when("button").isHigh().goTo("on")
                .endTransitions()
                .build();
    }

}
