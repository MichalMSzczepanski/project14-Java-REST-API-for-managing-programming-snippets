# JAVA REST API for managing programming snippets

## Concept 

It would be nice to have one source of snippets instead of 25 bookmarks, 5 spreadsheets and a few apps for managing similliar topics. so here's an API to gather snippets from around the world.

Api key "authentication" : 123456789

## Technical aspects

- Java 8 (javax.servlet-api; jackson-databind; mysql-connector-java)
- MySQL

No framework used in the name of practice.


## API communications

**JSON example**

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

Get all snippets from DB.
"/snippetAPI"
requires: api key in header
returns array of JSON files with snippets

Get snippet from DB by ID.
"/snipeptAPI?id=1"
requires: api key in header; parameter in URL
returns JSON file with specific snippet


/**POST**

Add snippet to DB.
"/snippetAPI"
requires: api key in header; JSON file in request body
returns: nothing


/**PUT**

Update snippet in DB by id.
"/snippetAPI?id=foo"
requires: api key in header; parameter in URL; JSON file in request body
returns: nothing


/**DELETE**

Delete snippet in DB by id.
"/snippetAPI?id=foo"
requires: api key in header; parameter in URL
returns: nothing

