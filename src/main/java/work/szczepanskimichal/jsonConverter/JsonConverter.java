package work.szczepanskimichal.jsonConverter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import work.szczepanskimichal.model.Author;
import work.szczepanskimichal.model.Snippet;

import java.time.LocalDateTime;
import java.time.Month;
import java.time.Year;

public class JsonConverter <O> {

    public String convertObjectToJson(O o) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            String json = mapper.writeValueAsString(o);
            System.out.println("ResultingJSONstring = " + json);
            System.out.println(json);
            return json;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Snippet convertJsonToSnippet(String[][] array) {
        Snippet snippet = new Snippet();
        snippet.setVisibility(array[1][0]);
        snippet.setAuthor(new Author(Integer.parseInt(array[2][0]), array[2][1], array[2][2], Boolean.valueOf(array[2][3])));
        snippet.setProgrammingLanguage(array[3][0]);
        snippet.setTitle(array[4][0]);
        snippet.setSnippet(array[5][0]);
            Integer yearCreated = Integer.parseInt(array[6][0]);
            Month monthCreated = Month.of(Integer.valueOf(array[6][1]));
            Integer dayOfMonthCreated = Integer.valueOf(array[6][2]);
            Integer hourCreated = Integer.valueOf(array[6][3]);
            Integer minuteCreated = Integer.valueOf(array[6][4]);
            Integer secondCreated = Integer.valueOf(array[6][5]);
        snippet.setCreated(LocalDateTime.of(yearCreated, monthCreated, dayOfMonthCreated, hourCreated, minuteCreated, secondCreated));
        Integer yearEdited = Integer.parseInt(array[7][0]);
        Month monthEdited = Month.of(Integer.valueOf(array[7][1]));
        Integer dayOfMonthEdited = Integer.valueOf(array[7][2]);
        Integer hourEdited = Integer.valueOf(array[7][3]);
        Integer minuteEdited = Integer.valueOf(array[7][4]);
        Integer secondEdited = Integer.valueOf(array[7][5]);
        snippet.setEdited(LocalDateTime.of(yearEdited, monthEdited, dayOfMonthEdited, hourEdited, minuteEdited, secondEdited));
        return snippet;
    }

}
