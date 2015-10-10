package se.kits.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class JsonHelper {
    private static final ObjectMapper MAPPER = new ObjectMapper();

    public static String getAsJson(Object object) {
        try {
            return MAPPER.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public static <R> R getAsObject(String s, Class<R> clazz) {
        try {
            return MAPPER.readValue(s.getBytes(), clazz);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
