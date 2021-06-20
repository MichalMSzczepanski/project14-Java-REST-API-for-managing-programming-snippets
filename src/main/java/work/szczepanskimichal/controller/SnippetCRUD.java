package work.szczepanskimichal.controller;

import work.szczepanskimichal.jsonConverter.JsonConverter;
import work.szczepanskimichal.model.Snippet;
import work.szczepanskimichal.model.SnippetList;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@WebServlet(name = "SnippetCRUD", value = "/snippetAPI")
public class SnippetCRUD extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        JsonConverter jsonConverter = new JsonConverter();
        SnippetList snippetList = new SnippetList();

        if (request.getParameter("id") != null) {
            // if parameter present - return specific snippet
            String snippet = jsonConverter.convertObjectToJson(snippetList.getSnippetById(Integer.valueOf(request.getParameter("id"))));
            response.getWriter().append(snippet);
        } else {
            // if parameter not present - return full list of snippets
            String[] snippetArray = new String[snippetList.getSnippetList().size()];
            for (int i = 0; i < snippetList.getSnippetList().size(); i++) {
                snippetArray[i] = jsonConverter.convertObjectToJson(snippetList.getSnippetList().get(i));
            }
            response.getWriter().append(Arrays.toString(snippetArray));
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

}
