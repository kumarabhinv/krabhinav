package com.av;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class utils {

    JsonNode path;

    private JsonNode jsonNode(String fileLocation) {
        File file = new File("src/main/resources/locators.locators");
        ObjectMapper mapper = new ObjectMapper();
        JsonNode root;
        {
            try {
                root = mapper.readTree(file);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        path = root.get("accept-all");
        return path;
    }

    protected boolean isArray(String fieldName, String fileLocation) {
        return jsonNode(fileLocation).get(fieldName).isArray();
    }

    protected String jsonReader(boolean isArray, int index, String fieldName){
        if(!isArray) {
            return path.get(fieldName).asText();
        } else {
            return path.get(fieldName).get(index).asText();
        }
    }

}
