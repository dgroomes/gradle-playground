package dgroomes;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {

    private static final Logger log = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        log.info(message());
    }

    public static String message() {
        return "Hello world!";
    }
}
