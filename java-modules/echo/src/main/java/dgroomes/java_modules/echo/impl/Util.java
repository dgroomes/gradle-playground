package dgroomes.java_modules.echo.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Note: this class is an implementation detail of the "echo" module and should not be exported. It should be invisible
 * to modules that import the "dgroomes.java_modules.echo" module. This is a benefit of the Java Module System!
 */
public class Util {

    /**
     * Deserialize a JSON document (in-memory string) into a JsonNode (Jackson's abstract representation of a JSON document).
     */
    public static JsonNode readTree(String json) {
        var objectMapper = new ObjectMapper();

        JsonNode node;
        try {
            node = objectMapper.readTree(json);
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException(String.format("The given string was not valid JSON! %s", json));
        }
        return node;
    }
}
