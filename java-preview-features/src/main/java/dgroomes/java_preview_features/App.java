package dgroomes.java_preview_features;

import java.util.TimeZone;

public class App {

    public static void main(String[] args) {
        var count = new App().countTimezones();
        System.out.printf("Found %d time zones%n", count);
    }

    public int countTimezones() {
        int count = 0;

        // This contrived example shows the use of the "Unnamed patterns and variables" preview language feature. We use
        // "_" in place of a variable name. This is a convenient way to not have to come up with a name, and to tell
        // tooling (linters, IDEs) that we indeed did not intend to use the variable.
        for (var _ : TimeZone.getAvailableIDs()) {
            count++;
        }

        return count;
    }
}
