![thumbnail-Desafio Java](https://github.com/jacqueline-oliveira/3355-java-desafio/assets/66698429/4b068d55-5cfc-480c-b94f-7d48b3c91eb3)

# Challenge


We are going to implement an application to store data about our favorite artists and songs in a relational database, being able to search for information by artists and query data about them through integration with the ChatGPT Gemini.
- You will need an Artist class, with the data to represent it;
- You will also need a specific class to represent the songs;
- For the artist, you can practice enum implementation, to define the artist type, for example: solo, duo or band;
- Remember to create the project through the [Spring Initializr] website (https:start.spring.io), where it is now possible to add Spring Data JPA and PostgreSQL dependencies; 
- Create a main class with the menu, with the desired options, such as: register artist, register music, search for music by artist, etc.; 
- Remember to extend the CommandLineRunner in the Spring class, overriding the run method to call the created menu.



## 🔨 Project objectives

- The objective of the project is to practice modeling classes and relationships using Spring Data JPA; 
- It is important to correctly describe and implement the relationship between Artist and Song, as an artist can be associated with more than one song; 
- A song should only be saved in the database if the Artist has been previously registered; 
- We will practice derived queries and JPQL to check if the artist is already registered and search for songs by a specific artist; 
- We will integrate with the ChatGPT API to search for information about a specific artist.


Good challenge!
