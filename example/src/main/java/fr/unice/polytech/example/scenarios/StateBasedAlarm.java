package fr.unice.polytech.example.scenarios;

import fr.unice.polytech.model.App;

import static fr.unice.polytech.builder.AppBuilder.*;

public class StateBasedAlarm implements Scenario {

    @Override
    public App getApp() {
        return application("stateBasedAlarm")
                    .brick(sensor("button", 9))
                    .brick(actuator("led", 10))
                .withState("on")
                    .set("led").toHigh()
                .endState()
                .withState("off").initial()
                    .set("led").toLow()
                .endState()
                .transitions()
                    .from("off").when("button").isHigh().goTo("on")
                    .from("on").when("button").isHigh().goTo("off")
                .endTransitions()
                .build();
    }

}
