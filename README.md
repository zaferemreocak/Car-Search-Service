# Car Search Service

Lite Spring Boot based project to implement dependency injection and create a standalone Java JAR application with 3 REST services.
Utilized this useful resource for the project : https://medium.com/edureka/what-is-dependency-injection-5006b53af782

### REST Services
	http://localhost:8080/[SERVICE NAME]

- Sample format for Subscriber type parameter:
    {
      "brand" : "Audi",
      "model" : "Q5",
      "type" : "Arazi,SUV&Pick-up"
    }
    
Car searcher simply looks for models.txt file under resources to load all the available cars which each of them has 3 properties: brand;model;type

###### [GET]		:	http://localhost:8080/print
This service fetches all car objects and prints them out on console to see the content.
In addition to that, returns a string message for successful execution.

###### [GET]		:	http://localhost:8080/search?key={user_input}
This service fetches single/multiple search results based on given user-defined key value.
In case a match, service adds matched object to an array list of Java then returns a JSON array as response body.

###### [PUT]		:	http://localhost:8080/criteria
This service checks whether a given car exists in the model list or not.
If exists, it returns matched cars via response body.
In order to consume incoming JSON object, Jackson API has been used.

