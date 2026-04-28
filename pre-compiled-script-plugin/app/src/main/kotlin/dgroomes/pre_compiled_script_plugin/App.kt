package dgroomes.pre_compiled_script_plugin

import tools.jackson.databind.ObjectMapper

data class Greeting(val message: String)

fun jsonifyGreeting(greeting: Greeting): String {
    return ObjectMapper().writeValueAsString(greeting)
}

fun main() {
    println(jsonifyGreeting(Greeting("Hello from a happy Gradle subproject's main method. This Gradle subproject happily omitted conventional Gradle config thanks to a pre-compiled script plugin")))
}
