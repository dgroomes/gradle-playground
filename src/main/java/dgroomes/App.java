package dgroomes;

public class App {
    public MyMessage getGreeting() {
        return new MyMessage("Hello world.");
    }

    public static void main(String[] args) {
        var greeting = new App().getGreeting();
        System.out.println(greeting);
    }
}
