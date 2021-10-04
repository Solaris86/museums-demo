## Museums demo API

A demo API for performing CRUD operations on Museum and Work entities.

### Tech Stack

* Java 11
* Spring Boot
* Spring Data JPA
* H2 in memory database

### H2 database

H2 is in memory database that can be accessed via http://localhost:8080/h2-console. Access parameters that can be used 
the testing purposes:
* JDBC URL: jdbc:h2:mem:demo
* User Name: admin
* Password: admin

### API documentation

API documentation can be accessed via http://localhost:8080/swagger-ui/index.html

### Postman collection

Postman collection is located inside src/main/resources/postman folder. It can be used for performing CRUD operations on 
Museum and Work entities.

