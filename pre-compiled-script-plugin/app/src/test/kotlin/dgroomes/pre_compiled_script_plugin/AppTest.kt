package dgroomes.pre_compiled_script_plugin

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class AppTest {

    @Test
    fun describeGreeting() {
        assertEquals(
            """{"message":"hello"}""",
            jsonifyGreeting(Greeting("hello")),
        )
    }
}
