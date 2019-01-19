package fr.unice.polytech.example.scenarios;

import fr.unice.polytech.model.App;

import static fr.unice.polytech.builder.AppBuilder.*;

public class DualCheckAlarm implements Scenario {

    @Override
    public App getApp() {
        return application("simpleAlarm")
                .brick(sensor("button1", 9))
                .brick(sensor("button2", 10))
                .brick(actuator("buzzer", 11))
                .withState("on")
                .set("buzzer").toHigh()
                .endState()
                .withState("off").initial()
                .set("buzzer").toLow()
                .endState()
                .transitions()
                .from("off")
                .when("button1").isHigh()
                .and("button2").isHigh()
                .goTo("on")
                .from("on").when("button1").isLow().goTo("off")
                .from("on").when("button2").isLow().goTo("off")
                .endTransitions()
                .build();
    }

}
