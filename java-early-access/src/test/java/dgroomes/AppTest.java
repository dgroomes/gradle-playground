package dgroomes;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AppTest {

    @Test
    void runMain() {
        App.main();
        assertEquals(1, 1);
    }
}
