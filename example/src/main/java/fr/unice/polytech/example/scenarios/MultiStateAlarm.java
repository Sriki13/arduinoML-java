package fr.unice.polytech.example.scenarios;

import fr.unice.polytech.model.App;

import static fr.unice.polytech.builder.AppBuilder.*;

public class MultiStateAlarm implements Scenario {

    @Override
    public App getApp() {
        return application("multiStateAlarm")
                    .brick(sensor("button", 9))
                    .brick(actuator("led", 10))
                    .brick(actuator("buzzer", 11))
                .withState("buzzing")
                    .set("led").toLow()
                    .set("buzzer").toHigh()
                .endState()
                .withState("off").initial()
                    .set("led").toLow()
                    .set("buzzer").toLow()
                .endState()
                .withState("lit")
                    .set("led").toHigh()
                    .set("buzzer").toLow()
                .endState()
                .transitions()
                    .from("off").when("button").isHigh().goTo("buzzing")
                    .from("buzzing").when("button").isHigh().goTo("lit")
                    .from("lit").when("button").isHigh().goTo("buzzing")
                .endTransitions()
                .build();
    }

}
