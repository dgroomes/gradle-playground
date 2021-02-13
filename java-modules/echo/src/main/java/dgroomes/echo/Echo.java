package dgroomes.echo;

public class Echo {

    /**
     * Echo some message back.
     * @param msg the message to echo
     * @return the echo
     */
    public static String echo(String msg) {
        return String.format("%s, %s, %s ...", msg, msg, msg);
    }
}
