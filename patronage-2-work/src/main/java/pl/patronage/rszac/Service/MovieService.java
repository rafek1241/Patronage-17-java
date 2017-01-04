package pl.patronage.rszac.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.patronage.rszac.Dao.MovieDao;
import pl.patronage.rszac.Entity.Actor;
import pl.patronage.rszac.Entity.Movie;

import java.util.Collection;
import java.util.LinkedHashSet;

@Service
public class MovieService {
    @Autowired
    private MovieDao movieDao;

    public Movie getMovieById(long id) {
        return this.movieDao.getMovieById(id);
    }

    public Collection<Movie> getAllMovies() {
        return this.movieDao.getAllMovies();
    }

    public void removeMovieById(long id) {
        this.movieDao.removeMovieById(id);
    }

    public void setActorToMovieById(int IdAct, int IdMovie) {
        this.movieDao.setActorToMovieById(IdAct, IdMovie);
    }

    public void remActorFromMovieById(int IdAct, int IdMov) {
        this.movieDao.remActorFromMovieById(IdAct, IdMov);
    }


    public void updateMovie(Movie movie) {
        this.movieDao.updateMovie(movie);
    }

    public void insertMovie(Movie movie) {
        this.movieDao.insertMovie(movie);
    }

    public void insertActorsToMovie(LinkedHashSet<Actor> list, int idMov) {
        this.movieDao.insertActorsToMovie(list, idMov);
    }
}
