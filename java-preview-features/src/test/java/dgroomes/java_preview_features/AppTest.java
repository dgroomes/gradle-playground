package dgroomes.java_preview_features;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class AppTest {

    @Test
    void message() {
        var app = new App();

        assertTrue(app.countTimezones() > 0);
    }
}
