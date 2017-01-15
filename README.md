# Patronage-17-java
### Second task

## Introduction

This is an application for rent movies. We can make a list of movies and create users which will be rent this movies.

## Structure of project
```
	*src/main/java
	*	/pl.patronage.rszac
	*	/pl.patronage.rszac/Controller
	*	/pl.patronage.rszac/Dao
	*	/pl.patronage.rszac/Entity
	*	/pl.patronage.rszac/Service
	*/src/test/java
	*	/pl.patronage.rszac.dao
```

##Technologies used:

1) Build: Maven

2) Web Services Framework: Spring Boot Starter

3) Testing: JUnit & Travis [![Build Status](https://travis-ci.org/rafek1241/Patronage-17-java.svg?branch=Workflow)](https://travis-ci.org/rafek1241/Patronage-17-java)

##Steps to run:

1) mvn clean package (in project folder)

2)
 * java -jar target/patronage-1-work-0.0.1-SNAPSHOT.jar
 * mvn spring-boot:run (in project folder)

**Project have been setup at `http://localhost:8080`**

##Requests

###Actors

####GET

`curl http://localhost:8080/actors`

>Get list of actors

`curl http://localhost:8080/actors/1`

>Get actor with id `1`

####POST

`curl -H "Content-Type: application/json" -X POST -d '{"id":"1","name":"xyz","surname":"xyz"}' http://localhost:8080/actors`

>Insert actor with id `"id":"1"`

####PUT

`curl -H "Content-Type: application/json" -X PUT -d '{"id":"1","name":"xyz","surname":"xyz"}' http://localhost:8080/actors`

>Update actor with id `"id":"1"`

####DELETE

`curl -v -X DELETE http://localhost:8080/actors/1`

>Delete actor with id `1`

###Movies

####GET

`curl http://localhost:8080/movies`

>Get list of movies

`curl http://localhost:8080/movies/1`

>Get movie with id `1`

`curl http://localhost:8080/movies/price/1`

>Get list of movies in price category `1` `(1 - "Nowości", 2 - "Hity", 3 - "Pozostale")`

`curl http://localhost:8080/movies/available`

>Get list of movies that aren't rented

####POST

`curl -H "Content-Type: application/json" -X POST -d '2' http://localhost:8080/movies/1/addActor`

>Insert Actor with id `2` to movie with id `1`

`curl -H "Content-Type: application/json" -X POST -d '[{"id":"1","name":"xyz","surname":"xyz"}]' http://localhost:8080/movies/1/addActors`

>Insert Actors to movie with id `1`

`curl -H "Content-Type: application/json" -X POST -d '{"id":"111","name":"xyz","category":"2",[{"id":"1","name":"xyz","surname":"xyz"}]}' http://localhost:8080/movies`

>Insert movie with id `"id":"111"`

####PUT

`curl -H "Content-Type: application/json" -X POST -d '{"id":"5","name":"xyz","category":"2",[{"id":"1","name":"xyz","surname":"xyz"}]}' http://localhost:8080/movies`

>Update movie with id `"id":"5"`

####DELETE

`curl -v -X DELETE http://localhost:8080/movies/1`

>Delete movie with id `1`

###Users

####GET

`curl http://localhost:8080/users`

>Get list of users

`curl http://localhost:8080/users/1/rent`

>Get list of rented movies from user with id `1`

####POST

`curl -H "Content-Type: application/json" -X POST -d '{"id": 1,"login": "test1","password": "test2","name": "test3","surname": "test4","rentedMovies": []}' http://localhost:8080/users/`

>Create user

`curl -H "Content-Type: application/json" -X POST -d '[1,2,3]' http://localhost:8080/users/1/rent`

>Rent movie

`curl -H "Content-Type: application/json" -X POST -d '50.05' http://localhost:8080/users/1/pay`

>Pay user

####DELETE
`curl -H "Content-Type: application/json" -X DELETE -d '[1,2,3]' http://localhost:8080/users/1/rent`

>Return movie

</n>

