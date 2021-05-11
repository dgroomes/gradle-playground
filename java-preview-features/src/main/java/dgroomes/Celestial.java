package dgroomes;

/**
 * A toy example illustrating Java's new language feature called "Sealed classes and interfaces". Uses code examples from
 * the JEP page https://openjdk.java.net/jeps/397.
 */
sealed interface Celestial
        permits Planet, Star {
}
