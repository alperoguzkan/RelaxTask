package utils;


import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class RequestHelper {

    public static String getJsonString(Object o) {
        ObjectMapper objectMapper = new ObjectMapper();
        //objectMapper.getSerializationConfig().withPropertyInclusion(JsonSerialize.Inclusion.NON_NULL);

        try {
            return objectMapper.writeValueAsString(o);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

}