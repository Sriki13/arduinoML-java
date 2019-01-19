package fr.unice.polytech.example.scenarios;

import fr.unice.polytech.model.App;

import static fr.unice.polytech.builder.AppBuilder.*;

public class SimpleAlarm implements Scenario {

    @Override
    public App getApp() {
        return application("simpleAlarm")
                .brick(sensor("button", 9))
                .brick(actuator("led", 10))
                .brick(actuator("buzzer", 11))
                .withState("on")
                .set("led").toHigh()
                .set("buzzer").toHigh()
                .endState()
                .withState("off").initial()
                .set("led").toLow()
                .set("buzzer").toLow()
                .endState()
                .transitions()
                .from("off").when("button").isHigh().goTo("on")
                .from("on").when("button").isLow().goTo("off")
                .endTransitions()
                .build();
    }

}
