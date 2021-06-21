package work.szczepanskimichal.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class SnippetList {

    private List<Snippet> snippetList;

    public SnippetList() {
        this.snippetList = new ArrayList<>();
        snippetList.add(new Snippet(1, "private", new Author(1, "name1", "email1@email.com", false), "java", "java spring - first snippet", "first snippet contents"));
        snippetList.add(new Snippet(2, "private", new Author(1, "name2", "email2@email.com", true), "java script", "java script - second snippet", "second snippet contents"));
    }

    public List<Snippet> getSnippetList() {
        return snippetList;
    }

    public void setSnippetList(List<Snippet> snippetList) {
        this.snippetList = snippetList;
    }

    public Snippet getSnippetById(Integer id) {
        return snippetList.get(id - 1);
    }

    public void addSnippet(Snippet snippet) throws IOException {
        snippetList.add(snippet);
    }

    public List<Snippet> deleteSnippet (int id) {
        snippetList = snippetList.stream()
                .filter(s -> s.getId() != id)
                .collect(Collectors.toList());
        return snippetList;
    }

    public void updateSnippetById (int id, Snippet snippet) {
        for (int i = 0; i < snippetList.size(); i++) {
            if(snippetList.get(i).getId() == id) {
                snippetList.set(i, snippet);
            }
        }
    }

    @Override
    public String toString() {
        return "SnippetList{" +
                "snippetList=" + snippetList +
                '}';
    }
}
