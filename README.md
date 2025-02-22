# Movie Challenge API

Backend challenge with Java. Manage movies, ratings and users

## Description

**This project is not meant to be published in production enviroment** <br>
Movie API with CRUD operations for movies, management of average movie ratings by users, and security handled with JWT to restrict operations based on user role
### Features
 
* Methods GET /movies and POST /auth/Login are public
* CRUD operations (create, update and delete) just for ADMIN users
* Any kind of user can rate a movie (only once)
* Authenticated users can get their rating movies
* Swagger documentation under path /v3/api-docs and /swagger-ui

## Getting Started

### Dependencies

* IDE of your preference (IntelliJ IDEA, Netbeans, vs code, etc...)
* Java openjdk11:latest
* Apache Maven

### Installing

* Configure your settings.xml for maven
* CHANGE the secret values on docker-compose.yml for your own build
* OR CHANGE the application.properties to run local
* Check the *.sql files for schema, data and triggers in database 

### Executing program

How to run the program 
 
for local environment, you can execute 

    mvn clean compile package -DskipTests=true

to build the jar and then run it. Make sure to change the properties for local purpose.

for docker, you can run docker compose

    docker-compose up

The build should start right away.

## Help

**IMPORTANT**: Example of params for search movies 

    ?size=10&page=0&sort=averageRating,desc

* by search={text inside name or synopsis} case-insensitive
* category={specific category} case-sensitive
* releaseYear={specific release year} equal

## Authors

Contributors names and contact info

ex. [LuisAraujo-SV](https://github.com/LuisAraujo-SV)

## Version History

* 1.0.0
  * add dockerfile and docker-compose.yml
  * Update README.md
  * Migrate Properties to docker compose file (For Production ENV use secrets)
* 0.0.5
  * Add Rating endpoints
  * Add Validation for password with regexp
  * Add Swagger / Swagger UI
  * Add Logger for some useful classes
  * Add Validation try / catch and javax.validation
* 0.0.4
  * CRUD Endpoints for Movies (with security)
  * Add Endpoint to register a new user (only admin)
* 0.0.3
  * Spring Security Configuration
  * Manage endpoints access by ROLE
  * JWT Interceptor
  * Login Endpoint Complete
* 0.0.2
  * Movies endpoints started (Pageable)
  * Filtering complete
  * Sorting complete
* 0.0.1
  * Initial Release
  * Repositories and Entities
  * Basic DTOs
  * Spring Boot default configuration
