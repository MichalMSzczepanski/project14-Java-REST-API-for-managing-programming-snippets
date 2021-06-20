package work.szczepanskimichal.model;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

public class SnippetList {

    private List<Snippet> snippetList;

    public SnippetList() {
        this.snippetList = new ArrayList<>();
        snippetList.add(new Snippet(1, "private", new Author(1, "name1", "email1@email.com", false), "java", "java spring - first snippet", "first snippet contents", LocalDateTime.of(2020, Month.JUNE, 29, 20, 30, 30), null));
        snippetList.add(new Snippet(2, "private", new Author(1, "name2", "email2@email.com", true), "java script", "java script - second snippet", "second snippet contents", LocalDateTime.of(2020, Month.JUNE, 29, 20, 30, 30), null));
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

    @Override
    public String toString() {
        return "SnippetList{" +
                "snippetList=" + snippetList +
                '}';
    }
}
