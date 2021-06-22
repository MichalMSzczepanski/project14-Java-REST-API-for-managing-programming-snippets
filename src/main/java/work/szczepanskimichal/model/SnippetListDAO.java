package work.szczepanskimichal.model;

import work.szczepanskimichal.Util.DBUtil;
import work.szczepanskimichal.Util.JsonConverter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SnippetListDAO {

    //author queries
    final static String createAuthor = "INSERT INTO authors VALUES (null, ?, ?, ?, ?)";
    final static String readAuthor = "SELECT * FROM authors WHERE apiKey LIKE ?;";
    final static String readAllAuthors = "SELECT * FROM authors";
    final static String deleteAuthor = "DELETE from authors WHERE apiKey LIKE ?";
    final static String updateAuthor = "UPDATE authors set name = ?, email = ?, handle = ?," +
            " isAdmin = ? WHERE apiKey LIKE ?";
    final static String validateApiKeyExistence = "SELECT * FROM authors where apiKey LIKE ?";

    // snippet queries
    final static String createSnippet = "INSERT INTO snippets VALUES (null, ?, ?, ?, ?, ?)";
    final static String readSnippet = "SELECT * FROM snippets WHERE snippetId LIKE ? AND authorId like ?";
    final static String readAllSnippets = "SELECT * FROM snippets";
    final static String readAllSnippetsOfAuthor = "SELECT * FROM snippets where authorId like ?";
    final static String deleteSnippet = "DELETE from snippets WHERE snippetId LIKE ? AND authorId like ?";
    final static String updateSnippet = "UPDATE snippets set visibility = ?, authorId = ?, programmingLanguage = ?, title = ?, snippet = ? WHERE snippetId LIKE ?;";

    static Connection connection = DBUtil.getConnection();

    public static Boolean validateApiKeyExistence(String apiKey) throws SQLException {
        Boolean apiKeyExists = false;
        PreparedStatement ps = connection.prepareStatement(validateApiKeyExistence);
        ps.setString(1, apiKey);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            apiKeyExists = true;
        }
        return apiKeyExists;
    }

    // add snippet to author based on api key
    public static void addSnippet(Snippet snippet, String apiKeyAuth) throws SQLException {
        // validate if apiKey in authorization matches JSON apiKey
        if (!snippet.getAuthor().getApiKey().equals(apiKeyAuth)) {
            System.out.println("flag1");
            // if not - logger in the future
        } else {
            // validate by apiKey if author exists
            System.out.println("flag2");
            Integer authorId = null;
            PreparedStatement ps1a = connection.prepareStatement(readAuthor);
            ps1a.setString(1, snippet.getAuthor().getApiKey());
            ResultSet rs1a = ps1a.executeQuery();
            if (!rs1a.next()) {
                // if resultset is empty - create author and fetch authorID
                System.out.println("flag3");
                PreparedStatement ps1b = connection.prepareStatement(createAuthor, PreparedStatement.RETURN_GENERATED_KEYS);
                ps1b.setString(1, snippet.getAuthor().getName());
                ps1b.setString(2, snippet.getAuthor().getEmail());
                ps1b.setString(3, snippet.getAuthor().getHandle());
                ps1b.setString(4, snippet.getAuthor().getApiKey());
                ps1b.executeUpdate();
                ResultSet rs1b = ps1b.getGeneratedKeys();
                if (rs1b.next()) {
                    System.out.println("flag4");
                    authorId = rs1b.getInt(1);
                }
            } else {
                // if resultset is not empty - fetch authorID
                authorId = rs1a.getInt(1);
                System.out.println("flag5");
            }
            PreparedStatement ps2 = connection.prepareStatement(createSnippet, PreparedStatement.RETURN_GENERATED_KEYS);
            ps2.setString(1, snippet.getVisibility());
            ps2.setInt(2, authorId);
            ps2.setString(3, snippet.getProgrammingLanguage());
            ps2.setString(4, snippet.getTitle());
            ps2.setString(5, snippet.getSnippet());
            ps2.executeUpdate();
            ResultSet rs2 = ps2.getGeneratedKeys();
            if (!rs2.next()) {
                System.out.println("flag6");
                // logger in the future
            }
        }
    }

    // get all snippets of author based on api key
    public static List<String> getAllSnippetsOfAuthor(String apiKeyAuth) throws SQLException {

        Author author = fetchAuthorByApiKey(apiKeyAuth);
        Integer authorId = author.getId();

        List<String> list = new ArrayList<>();
        PreparedStatement ps2 = connection.prepareStatement(readAllSnippetsOfAuthor);
        ps2.setInt(1, authorId);
        ResultSet rs2 = ps2.executeQuery();
        System.out.println("flag rs");
        System.out.println(rs2);
        while (rs2.next()) {
            System.out.println("flag rs in");
            Snippet snippet = new Snippet();
            snippet.setId(rs2.getInt(1));
            snippet.setVisibility(rs2.getString(2));
            snippet.setAuthor(author);
            snippet.setProgrammingLanguage(rs2.getString(4));
            snippet.setTitle(rs2.getString(5));
            snippet.setSnippet(rs2.getString(6));
            list.add(JsonConverter.convertObjectToJson(snippet));
            System.out.println("flag snippet");
            System.out.println(snippet);
        }
        System.out.println("flag list fin");
        System.out.println(list);
        return list;
    }

    // get one snippet based on snippet id afer validating author api key
    public static String getSnippetBySnippetId(String apiKeyAuth, int snippetId) throws SQLException {

        Author author = fetchAuthorByApiKey(apiKeyAuth);
        Integer authorId = author.getId();

        PreparedStatement ps2 = connection.prepareStatement(readSnippet);
        ps2.setInt(1, snippetId);
        ps2.setInt(2, authorId);
        ResultSet rs2 = ps2.executeQuery();
        Snippet snippet = new Snippet();
        while (rs2.next()) {
            snippet.setId(rs2.getInt(1));
            snippet.setVisibility(rs2.getString(2));
            snippet.setAuthor(author);
            snippet.setProgrammingLanguage(rs2.getString(4));
            snippet.setTitle(rs2.getString(5));
            snippet.setSnippet(rs2.getString(6));
        }
        return JsonConverter.convertObjectToJson(snippet);
    }

    public static Boolean updateSnippetBySnippetId(String apiKeyAuth, int snippetId, Snippet snippet) throws SQLException {

        Author author = fetchAuthorByApiKey(apiKeyAuth);
        Integer authorId = author.getId();

        PreparedStatement ps = connection.prepareStatement(updateSnippet);
        ps.setString(1, snippet.getVisibility());
        ps.setInt(2, authorId);
        ps.setString(3, snippet.getProgrammingLanguage());
        ps.setString(4, snippet.getTitle());
        ps.setString(5, snippet.getSnippet());
        ps.setInt(6, snippetId);

        if (ps.executeUpdate() == 1) {
            return true;
        } else {
            return false;
        }

    }

    //delete snippet by snippet id after validating author api key
    public static Boolean deleteSnippetBySnippetId(String apiKeyAuth, int snippetId) throws SQLException {

        Author author = fetchAuthorByApiKey(apiKeyAuth);
        Integer authorId = author.getId();

        PreparedStatement ps2 = connection.prepareStatement(deleteSnippet);
        ps2.setInt(1, snippetId);
        ps2.setInt(2, authorId);
        if (ps2.executeUpdate() == 1) {
            return true;
        } else {
            return false;
        }
    }

    public static Author fetchAuthorByApiKey(String apikeyAuth) throws SQLException {
        PreparedStatement ps1 = connection.prepareStatement(readAuthor);
        ps1.setString(1, apikeyAuth);
        ResultSet rs1 = ps1.executeQuery();
        Author author = new Author();
        while (rs1.next()) {
            author.setId(rs1.getInt(1));
            author.setName(rs1.getString(2));
            author.setEmail(rs1.getString(3));
            author.setHandle(rs1.getString(4));
            author.setApiKey(rs1.getString(5));
        }
        return author;
    }

}
