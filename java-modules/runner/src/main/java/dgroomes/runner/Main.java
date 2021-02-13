package dgroomes.runner;

import dgroomes.echo.Echo;

public class Main {

    public static void main(String[] args) {
        var msg = "hello!";
        System.out.printf("You say: %s%n", msg);
        var response = Echo.echo(msg);
        System.out.printf("You heard: %s%n", response);
    }
}
