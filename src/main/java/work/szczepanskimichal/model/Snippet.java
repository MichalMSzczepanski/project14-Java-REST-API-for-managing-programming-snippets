package work.szczepanskimichal.model;

import java.time.LocalDateTime;

public class Snippet {

    private Integer id;
    private String visibility;
    private Author author;
    private String programmingLanguage;
    private String title;
    private String snippet;
    private LocalDateTime created;
    private LocalDateTime edited;

    public Snippet() {
    }

    // constructor for data base
    public Snippet(String visibility, Author author, String programmingLanguage, String title, String snippet, LocalDateTime created, LocalDateTime edited) {
        this.visibility = visibility;
        this.author = author;
        this.programmingLanguage = programmingLanguage;
        this.title = title;
        this.snippet = snippet;
        this.created = created;
        this.edited = edited;
    }

    // constructor for tests
    public Snippet(Integer id, String visibility, Author author, String programmingLanguage, String title, String snippet, LocalDateTime created, LocalDateTime edited) {
        this.id = id;
        this.visibility = visibility;
        this.author = author;
        this.programmingLanguage = programmingLanguage;
        this.title = title;
        this.snippet = snippet;
        this.created = created;
        this.edited = edited;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getVisibility() {
        return visibility;
    }

    public void setVisibility(String visibility) {
        this.visibility = visibility;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public String getProgrammingLanguage() {
        return programmingLanguage;
    }

    public void setProgrammingLanguage(String programmingLanguage) {
        this.programmingLanguage = programmingLanguage;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSnippet() {
        return snippet;
    }

    public void setSnippet(String snippet) {
        this.snippet = snippet;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public LocalDateTime getEdited() {
        return edited;
    }

    public void setEdited(LocalDateTime edited) {
        this.edited = edited;
    }

    @Override
    public String toString() {
        return "Snippet{" +
                "id=" + id +
                ", visibility='" + visibility + '\'' +
                ", author=" + author +
                ", programmingLanguage='" + programmingLanguage + '\'' +
                ", title='" + title + '\'' +
                ", snippet='" + snippet + '\'' +
                ", created=" + created +
                ", edited=" + edited +
                '}';
    }
}
