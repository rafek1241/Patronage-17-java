package pl.patronage.rszac.Dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pl.patronage.rszac.Entity.Actor;
import pl.patronage.rszac.Entity.Movie;

import java.util.*;


@Repository
public class MovieDao {
    private static Map<Integer, Movie> movies = new HashMap<>();


//    static {
//        movies = new HashMap<Integer, Movie>() {
//
//
//            {
//                put(1, new Movie(1, "Przeminelo z wiatrem"));
//                put(2, new Movie(2, "Droga do nikad"));
//                put(3, new Movie(3, "Esencja życia"));
//            }
//        };
//
//    }

    @Autowired
    private ActorDao actorDao;


    public Movie getMovieById(long id) {
        return movies.get((int) id);
    }


    public void setActorToMovieById(int IdAct, int IdMovie) {
        actorDao = new ActorDao();
        Actor a1 = actorDao.getActorById(IdAct);
        Movie m1 = movies.get(IdMovie);
        Set<Actor> t1 = m1.getActors();
        if (!t1.contains(a1))
            t1.add(a1);

    }

    public void remActorFromMovieById(int IdAct, int IdMov) {
        actorDao = new ActorDao();
        Actor a1 = actorDao.getActorById(IdAct);
        Movie m1 = movies.get(IdMov);
        Set<Actor> t1 = m1.getActors();
        if (t1.contains(a1))
            t1.remove(a1);
    }

    public Collection<Movie> getAllMovies() {
        return movies.values();
    }

    public void removeMovieById(long id) {
        movies.remove((int) id);
    }

    public void updateMovie(Movie movie) {
        Movie m = movies.get(movie.getId());
        m.setName(movie.getName());
        m.setActors(movie.getActors());
    }

    public void insertMovie(Movie movie) {
        movies.put(movie.getId(), movie);
    }

    public void insertActorsToMovie(LinkedHashSet<Actor> list, int idMov) {
        Movie m = movies.get(idMov);
        Set<Actor> t1 = m.getActors();
        for (Actor a : list) {
            if (!t1.contains(a))
                t1.add(a);
        }
    }
}
