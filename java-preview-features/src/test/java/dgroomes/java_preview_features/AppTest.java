package dgroomes.java_preview_features;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AppTest {

    @Test
    void message() {
        assertEquals("'99' is a big number", App.inferDescriptin(99));
    }
}
