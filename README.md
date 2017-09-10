# InstantSearch
This application demos how to build an Autocomplete/Search Solution. 
Key to this solution is making use of Tenary Search Tree to efficiently store data and do fast prefix string searches (https://en.wikipedia.org/wiki/Ternary_search_tree)
(This was built as a solution to an ITASoftware hiring puzzle)

The current solution consists of the following components. 

1. SearchRest Interface: Built using SpringBoot and Spring MVC
2. SearchDatabase: Implemented with a Tenary Search Tree and a HashMap. 
3. WebInterface: Built with react
