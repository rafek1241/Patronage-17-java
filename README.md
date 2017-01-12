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
```diff
> GET
```
>**`/actors`** - return list of actors
>
> Response fields:
>- id
>- name 
>- surname

</n> 

>**`/actors/{id}`** - return/find actor by id
>
> Response fields:
>- id
>- name 
>- surname

</n> 

```diff
> POST
```
>**`/actors`** - add new actor or actors
>
>	Header: `Content-Type:application/json`
>
>  Fields:
>- id
>- name 
>- surname
>
> Response fields
>- id
>- name
>- surname

</n> 

```diff
> DELETE
```
>**`/actors/{id}`** - delete actor by id

</n> 

```diff
> PUT
```
>**`/actors/{id}`** - update actor by id
>
>	Header: `Content-Type:application/json`
>
>  Fields:
>- name 
>- surname
>

</n> 

###Movies
```diff
> GET
```
>**`/movies`** - return list of movies
>
> Response fields:
>- id
>- name 
>- actors
>- category (price category)
>- rented

</n>  

>**`/movies/available`** - return list of movies which isn't rented
>
> Response fields:
>- id
>- name 
>- actors
>- category (price category)
>- rented

</n>  

>**`/movies/{id}`** - return/find movie by id
>
> Response fields:
>- id
>- name
>- actors
>- category (price category)
>- rented

</n> 

>**`/movies/price/{id}`** - return/find movie by category price id
>
> Response fields:
>- id
>- name
>- actors
>- category (price category)
>- rented

</n> 

>**`/movies/{id}/addActor/{idA}`** - add actor with idA to movie with id

</n> 

```diff
> POST
```
>**`/movies`** - add new movie
>
>	Header: `Content-Type:application/json`
>
>  Fields:
>- id
>- name
>- actors
>
> Response fields
>- id
>- name
>- actors

</n> 


>**`/movies/addActors`** - add list of actors to the movie
>
>	Header: `Content-Type:application/json`
>
>  Fields:
>- list of actors
>- id of movie

</n>

```diff
> DELETE
```
>**`/movies/{id}`** - delete movie by id

</n> 

>**`/movies/{id}/remActor/{idA}`** - remove actor with idA from movie with id

</n>

```diff
> PUT
```
>**`/movies/{id}`** - update movie by id
>
>	Header: `Content-Type:application/json`
>
>  Fields:
>- name
>- actors

</n> 

###Users
```diff
> GET
```
>**`/users`** - get user list
>
> Response fields
>- id
>- name
>- surname
>- login
>- password
>- rentedMovies
>- balance (how much you owe to the store)

</n>

>**`/users/{id}/rent`** - get list of rented movies
>
>  Fields:
>- id
>
> Response fields
>- id
>- name
>- actors
>- category (price category)
>- rented

</n>

```diff
> POST
```
>**`/users`** - add new user
>
>	Header: `Content-Type:application/json`
>
>  Fields:
>- id
>- name
>- surname
>- login
>- password
>- rentedMovies
>- balance 
>
> Response fields
>- id
>- name
>- surname
>- login
>- password
>- rentedMovies
>- balance (how much you owe to the store)

</n>

>**`/users/{id}/rent`** - Rent movie by user
>
>	Header: `Content-Type:application/json`
>
>  Fields:
>- id
>- moviesId
>
> Response fields
>- true/false

</n>


>**`/users/{id}/pay`** - Pay off the user's balance
>
>	Header: `Content-Type:application/json`
>
>  Fields:
>- id
>- moviesId
>
> Response fields
>- true/false

</n>

```diff
> DELETE
```
>**`/users/{id}/rent`** - return movie

</n> 

</n>

