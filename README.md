# Patronage-17-java
### Second task

## Introduction

This is an application for rent movies. We can manipulate objects in following way:

* Display any objects (1 or all)
* Add objects to list (1 or all)
* Remove object from list (1)
* Update any objects existing in list

## Structure of project
```
	*src/main/java
	*	/pl.patronage.rszac
	*	/pl.patronage.rszac/Controller
	*	/pl.patronage.rszac/Dao
	*	/pl.patronage.rszac/Entity
	*	/pl.patronage.rszac/Service
	*/src/test/java
	*	/pl.patronage.rszac.Dao
```

##Technologies used:

1) Build: Maven

2) Web Services Framework: Spring Boot Starter

3) Testing: JUnit & Travis [![Build Status](https://travis-ci.org/rafek1241/Patronage-16-Java.svg?branch=Workflow-Rszac)](https://travis-ci.org/rafek1241/Patronage-16-Java)

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

## Todo:
**TODO (old)**
- [x] Display any objects (1 or all)
- [x] Add objects to list (1 or all)
- [x] Remove object from list (1)
- [x] Update any objects existing in list

- [x] Move previous project on repositorium
- [x] Make 3 price categories of movies - "Nowości, hity, pozostałe"
- [x] Rent 2 movies in the same time will give 25% discount.
- [x] If rent 3 movies the same time - 1 from category "Pozostale" is for free
- [x] User can have max 10 movies in the same time.
- [x] REST action in JSON format.
- [x] Data directly in application Code.

- [x] Find movie by price category
- [x] Find movie available (not rented)
- [x] Create user of Movie rent.
- [x] Rent movie by user.
- [x] Return movie by user.
- [x] Get list of movies rented by user.

- [x] Define 10 movies in each category at the start.
- [ ] Don't forget about unit tests!
