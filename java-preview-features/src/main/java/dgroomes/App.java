package dgroomes;

import java.util.List;
import java.util.Random;

public class App {

    public static void main(String[] args) {
        var app = new App();

        // Describe some random celestial objects.
        for (int i = 0; i < 5; i++) {
            var randomCelestialObject = app.randomCelestialObject();
            String msg;
            if (randomCelestialObject instanceof Planet planet) {
                msg = "It's %s, an instance of Planet. Its atmosphere is: %s".formatted(planet.getClass().getSimpleName(), planet.atmosphere());
            } else if (randomCelestialObject instanceof Star star) {
                msg = "It's an instance of Star. The start description is: %s".formatted(star.brightness());
            } else {
                throw new IllegalStateException("Did not find a match. When JEP 406 is 'Pattern Matching for switch' this branch can be removed. See https://openjdk.java.net/jeps/406");
            }
            System.out.println(msg);
        }
    }

    public Celestial randomCelestialObject() {
        var celestialObjects = List.of(new Earth(), new Mars(), new Star());
        var idx = new Random().nextInt(celestialObjects.size());
        return celestialObjects.get(idx);
    }
}
