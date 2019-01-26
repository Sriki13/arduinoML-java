package fr.unice.polytech.example.scenarios;

import fr.unice.polytech.model.App;

import static fr.unice.polytech.builder.AppBuilder.*;

public class ReportCustom implements Scenario {

    @Override
    public App getApp() {
        return application("reportScenario")
                    .brick(actuator("led", 10))
                    .brick(actuator("buzzer", 12))
                    .brick(sensor("button", 9))
                    .brick(sensor("button2", 8))
                .withState("final")
                    .set("buzzer").toLow()
                    .set("led").toLow()
                .endState()
                .withState("all_on")
                    .set("led").toHigh()
                .endState()
                .withState("buzzer_on")
                    .set("buzzer").toHigh()
                    .set("led").toLow()
                .endState()
                .withState("led_on")
                    .set("led").toHigh()
                .endState()
                .withState("off").initial().endState()
                .transitions()
                    .after("all_on", 600).goTo("final")
                    .from("all_on").when("button").isHigh().goTo("off")
                    .from("all_on").when("button2").isLow().goTo("led_on")

                    .from("buzzer_on").goTo("all_on")

                    .from("led_on").when("button").isHigh().goTo("buzzer_on")

                    .from("off").when("button").isHigh()
                                .and("button2").isHigh()
                    .goTo("led_on")
                .endTransitions()
                .build();
    }

}
