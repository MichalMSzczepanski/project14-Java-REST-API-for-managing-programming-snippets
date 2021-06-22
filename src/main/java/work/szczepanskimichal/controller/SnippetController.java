package work.szczepanskimichal.controller;

import work.szczepanskimichal.Util.JsonConverter;
import work.szczepanskimichal.model.SnippetListDAO;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.stream.Collectors;

@WebServlet(name = "SnippetCRUD", value = "/snippetAPI")
public class SnippetController extends HttpServlet {

    // get all snippets or snippet by id
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String apiKey = request.getHeader("authentication");
        try {
            if (SnippetListDAO.validateApiKeyExistence(apiKey)) {
                if (request.getParameter("id") != null) {
                    // if parameter "id" present - return specific snippet by id
                    int snippetId = Integer.valueOf(request.getParameter("id"));
                    response.getWriter().append(SnippetListDAO.getSnippetBySnippetId(apiKey, snippetId));
                } else {
                    // if parameter not present - return full list of snippets
                    response.getWriter().append((SnippetListDAO.getAllSnippetsOfAuthor(apiKey).toString()));
                }
            } else {
                response.getWriter().append("no api key found or api key doesn't exist");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    // post snippet
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        try {
            if (SnippetListDAO.validateApiKeyExistence(request.getHeader("authentication"))) {
                // tbc details why and how this works --->
                String newSnippetJsonString = request.getReader().lines().collect(Collectors.joining());
                // <---
                SnippetListDAO.addSnippet(JsonConverter.convertJsonToSnippet(newSnippetJsonString), request.getHeader("authentication"));
            } else {
                response.getWriter().append("no api key found or api key doesn't exist");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    // update snippet by id
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String apiKey = request.getHeader("authentication");
        try {
            if (SnippetListDAO.validateApiKeyExistence(apiKey)) {
                String updatedSnippetJsonString = request.getReader().lines().collect(Collectors.joining());
                SnippetListDAO.updateSnippetBySnippetId(apiKey, Integer.valueOf(request.getParameter("id")), JsonConverter.convertJsonToSnippet(updatedSnippetJsonString));
            } else {
                response.getWriter().append("no api key found or api key doesn't exist");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    // delete snippet by id
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String apiKey = request.getHeader("authentication");
        try {
            if (SnippetListDAO.validateApiKeyExistence(apiKey)) {
                SnippetListDAO.deleteSnippetBySnippetId(apiKey, Integer.valueOf(request.getParameter("id")));;
            } else {
                response.getWriter().append("no api key found or api key doesn't exist");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

}
