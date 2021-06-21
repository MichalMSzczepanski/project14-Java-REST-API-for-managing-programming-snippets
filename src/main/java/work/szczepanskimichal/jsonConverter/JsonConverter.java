package work.szczepanskimichal.jsonConverter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import work.szczepanskimichal.model.Author;
import work.szczepanskimichal.model.Snippet;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.Year;

public class JsonConverter <O> {

    private ObjectMapper mapper = new ObjectMapper();

    public String convertObjectToJson(O o) {
        try {
            return mapper.writeValueAsString(o);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Snippet convertJsonToSnippet(String string) {
        try {
            return mapper.readValue(string, Snippet.class);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

}
