package dgroomes;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class AppTest {

    @Test
    void message() {
        var app = new App();

        assertNotNull(app.randomCelestialObject());
    }
}
