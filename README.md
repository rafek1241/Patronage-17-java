# Patronage-16-Java
## Introduction

This is an application for managing actors and movies. We can manipulate objects in following way:

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

3) Testing: JUnit

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

</n>  

>**`/movies/{id}`** - return/find movie by id
>
> Response fields:
>- id
>- name
>- actors

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

##TODO

- [x] Display any objects (1 or all)
- [x] Add objects to list (1 or all)
- [x] Remove object from list (1)
- [x] Update any objects existing in list
- [x] Unit tests (JUnit)
