package work.szczepanskimichal.model;

import work.szczepanskimichal.Util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SnippetListDAO {

    //author queries
    final static String createAuthor = "INSERT INTO authors VALUES (null, ?, ?, ?, ?, ?)";
    final static String readAuthor = "SELECT * FROM authors WHERE apiKey LIKE ?;";
    final static String readAllAuthors = "SELECT * FROM authors";
    final static String deleteAuthor = "DELETE from authors WHERE apiKey LIKE ?";
    final static String updateAuthor = "UPDATE authors set name = ?, email = ?, handle = ?," +
            " isAdmin = ? WHERE apiKey LIKE ?";

    // snippet queries
    final static String createSnippet = "INSERT INTO snippets VALUES (null, ?, ?, ?, ?, ?)";
    final static String readSnippet = "SELECT * FROM snippets WHERE snippetId LIKE ?";
    final static String readAllSnippets = "SELECT * FROM snippets";
    final static String deleteSnippet = "DELETE from snippets WHERE snippetId LIKE ?;";
    final static String updateSnippet = "UPDATE snippets set visibility = ?, authorId = ?, programmingLanguage = ?, title = ?, snippet = ? WHERE snippetId LIKE ?;";

    static Connection connection = DBUtil.getConnection();

    public static void addSnippet(Snippet snippet, String apiKeyAuth) throws SQLException {
        // validate if apiKey in authorization matches JSON apiKey
        if (!snippet.getAuthor().getApiKey().equals(apiKeyAuth)) {
            // if not - logger in the future
        } else {
            // validate by apiKey if author exists
            Integer authorId = null;
            PreparedStatement ps1a = connection.prepareStatement(readAuthor);
            ps1a.setString(1, snippet.getAuthor().getApiKey());
            ResultSet rs1a = ps1a.executeQuery();
            if (!rs1a.next()) {
                // if resultset is empty - create author and fetch authorID
                PreparedStatement ps1b = connection.prepareStatement(createAuthor, PreparedStatement.RETURN_GENERATED_KEYS);
                ps1b.setString(1, snippet.getAuthor().getName());
                ps1b.setString(2, snippet.getAuthor().getEmail());
                ps1b.setString(3, snippet.getAuthor().getHandle());
                ps1b.setString(4, snippet.getAuthor().getApiKey());
                ps1b.setBoolean(5, false);
                ps1b.executeUpdate();
                ResultSet rs1b = ps1b.getGeneratedKeys();
                if (rs1b.next()) {
                    authorId = rs1b.getInt(1);
                }
            } else {
                // if resultset is not empty - fetch authorID
                authorId = rs1a.getInt(1);
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
                // logger in the future
            }
        }
    }

}
