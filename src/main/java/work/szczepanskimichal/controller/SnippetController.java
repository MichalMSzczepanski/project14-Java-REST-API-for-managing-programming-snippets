package work.szczepanskimichal.controller;

import work.szczepanskimichal.Util.JsonConverter;
import work.szczepanskimichal.model.Snippet;
import work.szczepanskimichal.model.SnippetList;
import work.szczepanskimichal.model.SnippetListDAO;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet(name = "SnippetCRUD", value = "/snippetAPI")
public class SnippetController extends HttpServlet {

    // json converter object has methods to parse json objects and objects
    JsonConverter jsonConverter = new JsonConverter();
    // for now initialize snippetList with two snippets (later communicate with DAO classes and DB)
    SnippetList snippetList = new SnippetList();
    // static final api key for the time being
    static final String apiKey = "123456789";

    // get all snippets or snippet by id
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            if (SnippetListDAO.validateApiKeyExistence(request.getHeader("authentication"))) {
                String apiKey = request.getHeader("authentication");
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            if (SnippetListDAO.validateApiKeyExistence(request.getHeader("authentication"))) {
                // tbc details why and how this works --->
                String newSnippetJsonString = request.getReader().lines().collect(Collectors.joining());
                // <---
                SnippetListDAO.addSnippet(jsonConverter.convertJsonToSnippet(newSnippetJsonString), request.getHeader("authentication"));
            } else {
                response.getWriter().append("no api key found or api key doesn't exist");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    // update snippet by id
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if (request.getHeader("authentication").equals(apiKey)) {
            String updatedSnippetJsonString = request.getReader().lines().collect(Collectors.joining());
            snippetList.updateSnippetById(Integer.parseInt(request.getParameter("id")), jsonConverter.convertJsonToSnippet(updatedSnippetJsonString));
        } else {
            response.getWriter().append("no api key found or api key doesn't exist");
        }
    }

    // delete snippet by id
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if (request.getHeader("authentication").equals(apiKey)) {
            snippetList.deleteSnippet(Integer.parseInt(request.getParameter("id")));
        } else {
            response.getWriter().append("no api key found or api key doesn't exist");
        }
    }

}
