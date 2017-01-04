package pl.patronage.rszac.Dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pl.patronage.rszac.Entity.Actor;
import pl.patronage.rszac.Entity.Movie;

import java.util.*;


@Repository
public class MovieDao {
    private static Map<Integer, Movie> movies = new HashMap<>();

    private static PriceCatDao priceCategories = new PriceCatDao();

    static {
        movies = new HashMap<Integer, Movie>() {


            {
                put(1, new Movie(1, "Przeminelo z wiatrem", priceCategories.getCategoryById(1)));
                put(2, new Movie(2, "Droga do nikad", priceCategories.getCategoryById(1)));
                put(3, new Movie(3, "Esencja życia", priceCategories.getCategoryById(1)));
                put(4, new Movie(4, "Przeminelo z wiatrem 2", priceCategories.getCategoryById(1)));
                put(5, new Movie(5, "Droga do nikad 2", priceCategories.getCategoryById(1)));
                put(6, new Movie(6, "Esencja życia 2", priceCategories.getCategoryById(1)));
                put(7, new Movie(7, "Przeminelo z wiatrem 3", priceCategories.getCategoryById(1)));
                put(8, new Movie(8, "Droga do nikad 3", priceCategories.getCategoryById(1)));
                put(9, new Movie(9, "Esencja życia 3", priceCategories.getCategoryById(1)));
                put(10, new Movie(10, "Terminator", priceCategories.getCategoryById(1)));
                put(11, new Movie(11, "Smierc na drodze", priceCategories.getCategoryById(2)));
                put(12, new Movie(12, "Male drozdze i ich nastepstwa", priceCategories.getCategoryById(2)));
                put(13, new Movie(13, "trolle", priceCategories.getCategoryById(2)));
                put(14, new Movie(14, "trolle 2", priceCategories.getCategoryById(2)));
                put(15, new Movie(15, "Droga 2", priceCategories.getCategoryById(2)));
                put(16, new Movie(16, "samo życie 2", priceCategories.getCategoryById(2)));
                put(17, new Movie(17, "Pożycie 3", priceCategories.getCategoryById(2)));
                put(18, new Movie(18, "Lubie herbatniki 3", priceCategories.getCategoryById(2)));
                put(19, new Movie(19, "Esencja  3", priceCategories.getCategoryById(2)));
                put(20, new Movie(20, "Smieciowa", priceCategories.getCategoryById(2)));
                put(21, new Movie(21, "Robota", priceCategories.getCategoryById(3)));
                put(22, new Movie(22, "Na drodze", priceCategories.getCategoryById(3)));
                put(23, new Movie(23, "Nie wiem", priceCategories.getCategoryById(3)));
                put(24, new Movie(24, "Co mogę jeszcze", priceCategories.getCategoryById(3)));
                put(25, new Movie(25, "wpisac", priceCategories.getCategoryById(3)));
                put(26, new Movie(26, "walczę 2", priceCategories.getCategoryById(3)));
                put(27, new Movie(27, "O samo 3", priceCategories.getCategoryById(3)));
                put(28, new Movie(28, "Spelnienie zadania 3", priceCategories.getCategoryById(3)));
                put(29, new Movie(29, "Zobaczymy  3", priceCategories.getCategoryById(3)));
                put(30, new Movie(30, "Czy dobrze 2", priceCategories.getCategoryById(3)));
            }
        };

    }

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


    public Collection<Movie> getMovieByPriceCatId(int id) {
        if (priceCategories.getCategoryById(id) == null) {
            return null;
        }
        Set<Movie> movs = new HashSet<>();
        Iterator<Movie> It = movies.values().iterator();
        while (It.hasNext()) {
            Movie mov = It.next();
            if (mov.getCategory() == priceCategories.getCategoryById(id)) {
                movs.add(movies.get(mov.getId()));
            }
        }

        return movs;
    }

    public Collection<Movie> getAllMoviesAvailable() {
        Set<Movie> movs = new HashSet<>();
        Iterator<Movie> It = movies.values().iterator();
        while (It.hasNext()) {
            Movie mov = It.next();
            if (mov.isRented() == false) {
                movs.add(movies.get(mov.getId()));
            }
        }

        return movs;
    }
}