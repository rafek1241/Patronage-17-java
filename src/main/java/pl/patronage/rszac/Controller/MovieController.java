package pl.patronage.rszac.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.patronage.rszac.entity.Actor;
import pl.patronage.rszac.entity.Movie;
import pl.patronage.rszac.service.MovieService;

import java.util.Collection;
import java.util.LinkedHashSet;

@RestController
@RequestMapping("/movies")
public class MovieController {
    @Autowired
    private MovieService movieService;

    @RequestMapping(method = RequestMethod.GET)
    public Collection<Movie> getAllMovies() {
        return movieService.getAllMovies();

    }

    @RequestMapping(value = "/available", method = RequestMethod.GET)
    public Collection<Movie> getAllMoviesAvailable() {
        return movieService.getAllMoviesAvailable();

    }

    @RequestMapping(value = "/price/{id}", method = RequestMethod.GET)
    public Collection<Movie> getMoviesByPriceCategory(@PathVariable("id") int id) {
        return this.movieService.getMoviesByPriceCategory(id);
    }

    @RequestMapping(value = "/{idM}/addActor", method = RequestMethod.POST, consumes = org.springframework.http.MediaType.APPLICATION_JSON_VALUE)
    public void setActorToMovieById(@RequestBody int IdAct, @PathVariable("idM") int IdMovie) {
        this.movieService.setActorToMovieById(IdAct, IdMovie);
    }

    @RequestMapping(value = "/{idM}/addActors", method = RequestMethod.POST, consumes = org.springframework.http.MediaType.APPLICATION_JSON_VALUE)
    public void addActorsToMovie(@RequestBody LinkedHashSet<Actor> list, @PathVariable("idM") int IdMov) {
        movieService.insertActorsToMovie(list, IdMov);
    }

    @RequestMapping(value = "/{idM}/remActor/{idA}", method = RequestMethod.DELETE)
    public void remActorFromMovieById(@PathVariable("idA") int idAct, @PathVariable("idM") int IdMov) {
        this.movieService.remActorFromMovieById(idAct, IdMov);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Movie getMovieById(@PathVariable("id") long id) {
        return this.movieService.getMovieById(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void removeMovieById(@PathVariable("id") long id) {
        movieService.removeMovieById(id);
    }

    @RequestMapping(method = RequestMethod.PUT, consumes = org.springframework.http.MediaType.APPLICATION_JSON_VALUE)
    public void updateMovie(@RequestBody Movie movie) {
        movieService.updateMovie(movie);
    }

    @RequestMapping(method = RequestMethod.POST, consumes = org.springframework.http.MediaType.APPLICATION_JSON_VALUE)
    public void insertMovie(@RequestBody Movie movie) {
        movieService.insertMovie(movie);
    }

}
