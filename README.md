# InstantSearch
This application demos how to build an Autocomplete/Search Solution. 
This was built as a solution to an ITASoftware hiring puzzle https://github.com/mathaix/InstantSearch/wiki/Problem-Description---Instant-Search)

Key to this solution is making use of Tenary Search Tree to efficiently store data and do fast prefix string searches (https://en.wikipedia.org/wiki/Ternary_search_tree)

# Components
The current solution consists of the following components. 

1. SearchRest Interface: Built using SpringBoot and Spring MVC
2. SearchDatabase: Implemented with a Tenary Search Tree and a HashMap. 
3. WebInterface: Built with React

# Building and Running
1. Clone repository
2. cd into folder. 
3. Run ./mvnw spring-boot:run
4. Navigate to http://localhost:8080 
5. Make Searches: eg "Bos", "Rhode Island" etc
