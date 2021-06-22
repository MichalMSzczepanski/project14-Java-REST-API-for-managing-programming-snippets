package work.szczepanskimichal.model;

public class Author {
    private Integer id;
    private String name;
    private String email;
    private String handle;
    private String apiKey;

    public Author() {
    }

    public Author(String name, String email, String handle, String apiKey) {
        this.name = name;
        this.email = email;
        this.handle = handle;
        this.apiKey = apiKey;
    }

    public Author(Integer id, String name, String email, String handle, String apiKey) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.handle = handle;
        this.apiKey = apiKey;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getHandle() {
        return handle;
    }

    public void setHandle(String handle) {
        this.handle = handle;
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    @Override
    public String toString() {
        return "Author{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", handle='" + handle + '\'' +
                ", apiKey='" + apiKey + '\'' +
                '}';
    }
}
