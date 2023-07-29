package dgroomes;

import java.util.List;
import java.util.Random;

public class App {

    public static void main(String[] args) {
        var app = new App();

        // Describe some random celestial objects.
        for (int i = 0; i < 5; i++) {
            var randomCelestialObject = app.randomCelestialObject();
            String msg = switch (randomCelestialObject) {
                case Planet planet -> {
                    var name = planet.getClass().getSimpleName();
                    var atmosphere = planet.atmosphere();
                    yield "It's %s, an instance of Planet. Its atmosphere is: %s".formatted(name, atmosphere);
                }
                case Star star -> "It's an instance of Star. It says: %s".formatted(star.brightMessage());
            };
            System.out.println(msg);
        }
    }

    public Celestial randomCelestialObject() {
        var celestialObjects = List.of(new Earth(), new Mars(), new Star());
        var idx = new Random().nextInt(celestialObjects.size());
        return celestialObjects.get(idx);
    }
}
