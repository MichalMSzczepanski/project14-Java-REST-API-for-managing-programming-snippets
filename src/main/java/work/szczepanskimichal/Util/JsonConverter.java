package work.szczepanskimichal.Util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import work.szczepanskimichal.model.Snippet;

import java.io.IOException;


public class JsonConverter {

    private static ObjectMapper mapper = new ObjectMapper();

    public static String convertObjectToJson(Snippet snippet) {
        try {
            return mapper.writeValueAsString(snippet);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Snippet convertJsonToSnippet(String string) {
        try {
            return mapper.readValue(string, Snippet.class);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

}
