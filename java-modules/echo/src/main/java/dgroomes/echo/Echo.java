package dgroomes.echo;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Echo {

    /**
     * Echo some message back.
     * @param msg the message to echo
     * @return an echo of the message
     */
    public static String echo(String msg) {
        return String.format("%s, %s, %s ...", msg, msg, msg);
    }

    /**
     * Echo the "message" field contained in JSON
     * @param json the JSON that should contain a "message" field
     * @return an echo of the "message" field
     * @throws IllegalArgumentException if the JSON is invalid or does not contain a 'message' field
     */
    public static String echoFromJson(String json) {
        var objectMapper = new ObjectMapper();

        JsonNode node;
        try {
            node = objectMapper.readTree(json);
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException(String.format("The given string was not valid JSON! %s", json));
        }

        var msgNode = node.get("message");
        if (msgNode == null) {
            throw new IllegalArgumentException(String.format("The given JSON does not contain a 'message' field! %s", json));
        }

        var msg = msgNode.textValue();
        return echo(msg);
    }
}
