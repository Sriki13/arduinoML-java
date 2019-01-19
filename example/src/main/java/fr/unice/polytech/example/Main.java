package fr.unice.polytech.example;

import fr.unice.polytech.example.scenarios.MultiStateAlarm;
import fr.unice.polytech.example.scenarios.Scenario;

import static fr.unice.polytech.gen.ArduinoCodeGenerator.generator;

public class Main {

    private static final String HELP = "" +
            "Specify which example to translate and pass its number as arg.\n" +
            "1 - 4: Basic scenarios:\n" +
            "------------------------------\n" +
            "    1. Very simple alarm: Pushing a button activates a LED and a buzzer. Releasing the button switches\n" +
            " the actuators off.\n" +
            "    2. Dual-check alarm: Pushing a button will trigger a buzzer if and only if two buttons are pushed at\n" +
            "the very same time. Releasing at least one of the button stop the sound.\n" +
            "    3. State-based alarm: Pushing the button once switch the system in a mode where the LED is switched\n" +
            "on. Pushing it again switches it off.\n" +
            "    4. Multi-state alarm: Pushing the button starts the buzz noise. Pushing it again stop the buzzer and\n" +
            "switch the LED on. Pushing it again switch the LED off, and makes the system ready to make noise\n" +
            "again after one push, and so on.\n" +
            "--------------------------------\n" +
            "5: Extension scenario\n" +
            "--------------------------------\n" +
            "    5. Alan wants to define a state machine where LED1 is switched on after\n" +
            "a push on button B1 and switched off 800ms after, waiting again for a new push on B1.";

    public static void main(String[] args) {
//        if (args.length == 0) {
//            System.out.println(HELP);
//            return;
//        }
        Scenario scenario = new MultiStateAlarm();
        System.out.println(generator(scenario.getApp()).getCode());
    }

}
