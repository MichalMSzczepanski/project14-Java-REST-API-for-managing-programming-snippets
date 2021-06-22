# JAVA REST API for managing programming snippets

## Concept 

It would be nice to have one source of snippets instead of 25 bookmarks, 5 spreadsheets and a few apps for managing similliar topics. so here's an API to gather snippets from around the world.

Default user API key required in HTTP header >> "authentication" : 123456789

## Technical aspects

- Java 8 (javax.servlet-api; jackson-databind; mysql-connector-java)
- MySQL

No framework used in the name of practice.


## API communications

**JSON example (full)**

{
"id":1,
"visibility":"private",
"author": {
    "id":1,
    "name":"name1",
    "email":"email1@email.com",
    "admin":false
    },
"programmingLanguage":"java",
"title":"java spring - first snippet",
"snippet":"first snippet contents"
}


/**GET**

Get all snippets from DB
"/snippetAPI"
requires: API key in HTTP header
returns array of JSON strings with snippets

Get snippet from DB by ID.
"/snipeptAPI?id=number"
requires: API key in HTTP header; snippet id parameter in URL
returns JSON string with specific snippet


/**POST**

Add snippet to DB.
"/snippetAPI"
requires: API key in HTTP header; JSON file in request body
JSON file fields requered for creating snippet: {"visibility":"normal/ private","author":{"name":"name","email":"email@email.com","handle":"handle","apiKey":"apiKey"},"programmingLanguage":"language","title":"snippet title","snippet":"snippet conntents"}
returns: nothing


/**PUT**

Update snippet in DB
"/snippetAPI?id=number"
requires: API key in HTTP header; parameter in URL; JSON file in request body
JSON file fields required for updating snippet: {"visibility":"foo","author":{"apiKey":"123456789"},"programmingLanguage"bar","title":"lorem","snippet":"ipsum"}
returns: nothing


/**DELETE**

Delete snippet in DB by snippet id
"/snippetAPI?id=number"
requires: API key in HTTP header; parameter in URL
returns: nothing

