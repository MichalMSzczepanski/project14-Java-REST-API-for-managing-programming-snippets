# JAVA REST API for managing programming snippets

## Concept 

It would be nice to have one source of snippets instead of 25 bookmarks, 5 spreadsheets and a few apps for managing similliar topics. so here's an API to gather snippets from around the world.

## Technical aspects

- Java 8 (javax.servlet-api; jackson-databind; mysql-connector-java)
- MySQL

No framework used in the name of practice.


## API communications

Communicating with the API requires an API key in the HTTP header (key: "authentication", value: 123456789)

REST API available at: https://programming-snippet-rest-api.herokuapp.com/snippetAPI

### JSON example (all fields) 

<pre>{  
"id":1, // int auto increment,  
"visibility":"private",  // varchar(255)
"author": {  
    "id":1,  // varchar(255)
    "name":"authorName",  // varchar(255)
    "email":"authorEmail@email.com", // varchar(255)
    "handle":"authorHandle"}, // varchar(255)
    "apiKey":"apiKey"}, // varchar(255)
    },  
"programmingLanguage":"programmingLanguage", //varchar(255)
"title":"snippetTitle" //varchar(255)  
"snippet":"snippetContent" // varchar(2000) 
}</pre>

<br><hr><br>

### 1. GET

1.1 Get all snippets from DB at "https://programming-snippet-rest-api.herokuapp.com/snippetAPI/snippetAPI"  
**requires:** 
* API key in HTTP header  

**returns:** array of JSON strings with snippets  

1.2 Get snippet from DB by ID at "https://programming-snippet-rest-api.herokuapp.com/snippetAPI/snipeptAPI?id=number"  
**requires:** 
* API key in HTTP header; 
* snippet id parameter in URL  

**returns:** JSON string with specific snippet  

<br><hr><br>

### 2. POST

2.1 Add snippet to DB at "https://programming-snippet-rest-api.herokuapp.com/snippetAPI/snippetAPI"  
**requires:** 
* API key in HTTP header; 
* JSON file in request body  

**JSON file fields requered for creating snippet:**  
<pre>
{
"visibility":"normal",
"author": {
    "name":"name",
    "email":"email@email.com",
    "handle":"handle",
    "apiKey":"apiKey"},
"programmingLanguage":"language",
"title":"snippet title",
"snippet":"snippet conntents"
}</pre> 
**returns:** nothing  

<br><hr><br>

### PUT

Update snippet in DB at "https://programming-snippet-rest-api.herokuapp.com/snippetAPI/snippetAPI?id=number"  
**requires:** 
* API key in HTTP header; 
* parameter in URL; 
* JSON file in request body  

**JSON file fields required for updating snippet:**  
<pre>
{
"visibility":"foo",
"author":{
    "apiKey":"123456789"},
"programmingLanguage"bar",
"title":"lorem",
"snippet":"ipsum"
}</pre>

**returns:** nothing

<br><hr><br>

### DELETE

Delete snippet in DB by snippet id at "https://programming-snippet-rest-api.herokuapp.com/snippetAPI/snippetAPI?id=number"  
**requires:** 
* API key in HTTP header; 
* parameter in URL 
 
**returns:** nothing

