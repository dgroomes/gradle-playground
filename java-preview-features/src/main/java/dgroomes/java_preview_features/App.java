package dgroomes.java_preview_features;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import static java.lang.System.out;

public class App {

    static void main() {
        var javaVersion = System.getProperty("java.version");
        out.printf("Hello World! I am running in Java %s%n", javaVersion);

        var rnd = ThreadLocalRandom.current();
        List<Object> elements = List.of(1, 20, 99, "abc", "XYZ", 0f);
        var randomIdx = rnd.nextInt(elements.size());
        var randomElement = elements.get(randomIdx);

        var description = inferDescriptin(randomElement);
        out.println(description);
    }

    public static String inferDescriptin(Object randomElement) {

        // Using 'int i' in the switch case is possible because of the preview language feature.
        var fmt = switch (randomElement) {
            case int i when i > 90 -> "'%d' is a big number";
            case int i when i < 10 -> "'%d' is a little number";
            case int _ -> "'%d' is a medium number";
            case String s when !s.isEmpty() && Character.isUpperCase(s.codePointAt(0)) ->
                    "'%s' is an uppercased string";

            case String s when !s.isEmpty() && Character.isLowerCase(s.codePointAt(0)) -> "'%s' is a lowercased string";

            case String s when s.isEmpty() -> "'%s' is an empty string";

            case String s -> "'%s' is a string, but does not start with an uppercase/lowercase letter";

            default -> "Not sure what '%s' is";
        };

        return fmt.formatted(randomElement);
    }
}
