package dgroomes.runner;

import dgroomes.echo.Echo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {

    private static final Logger log = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        {
            var msg = "hello!";
            log.info("You say: {}", msg);
            var response = Echo.echo(msg);
            log.info("You heard: {}", response);
        }

        {
            var msg = "{ \"message\": \"hello from JSON!\" }";
            log.info("You say: {}", msg);
            var response = Echo.echoFromJson(msg);
            log.info("You heard: {}", response);
        }
    }
}
