package pl.patronage.rszac.Dao;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import pl.patronage.rszac.Entity.Actor;
import pl.patronage.rszac.Entity.Movie;

import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

public class MovieDaoTest {
    @Autowired
    MovieDao movies;
    @Autowired
    ActorDao actors;
    Movie a, b, c;
    Actor a1, a2, a3;

    @Before
    public void setUp() throws Exception {
        //ta metoda będzie wywołana przed każdym testem
        movies = new MovieDao();
        actors = new ActorDao();
        Set<Actor> list = new LinkedHashSet<>();
        a1 = new Actor(1, "Jan", "Kowalski");
        a2 = new Actor(2, "Barbara", "Kowalska");
        a3 = new Actor(3, "Konstanty", "Galczynski");
        list.add(a1);
        list.add(a2);
        a = new Movie(1, "Movie A");
        b = new Movie(2, "Movie B");
        c = new Movie(3, "Movie C", list);
    }


    @After
    public void tearDown() throws Exception {
        if (movies.getAllMovies().size() != 0) {
            Iterator<Movie> it = movies.getAllMovies().iterator();
            while (it.hasNext()) {
                it.next();
                it.remove();
            }

        }

    }

    @Test
    public void getMovieById() throws Exception {
        movies.insertMovie(a);
        movies.insertMovie(c);
        Assert.assertEquals(movies.getMovieById(c.getId()), c);
        Assert.assertEquals(movies.getMovieById(a.getId()), a);
        Assert.assertNull(movies.getMovieById(b.getId()));
    }

    @Test
    public void setActorToMovieById() throws Exception {
        actors.insertActor(a1);
        actors.insertActor(a2);
        movies.insertMovie(a);
        movies.setActorToMovieById(a1.getId(), a.getId());
        movies.setActorToMovieById(a2.getId(), a.getId());
        Assert.assertTrue(movies.getMovieById(a.getId()).getActors().contains(a1));
        Assert.assertTrue(movies.getMovieById(a.getId()).getActors().contains(a2));
        Assert.assertFalse(movies.getMovieById(a.getId()).getActors().contains(a3));
    }

    @Test
    public void remActorFromMovieById() throws Exception {
        actors.insertActor(a1);
        actors.insertActor(a2);
        actors.insertActor(a3);
        movies.insertMovie(c);
        movies.remActorFromMovieById(a2.getId(), c.getId());
        movies.remActorFromMovieById(a1.getId(), c.getId());
        Assert.assertFalse(movies.getMovieById(c.getId()).getActors().contains(a1));
        Assert.assertFalse(movies.getMovieById(c.getId()).getActors().contains(a2));
        Assert.assertFalse(movies.getMovieById(c.getId()).getActors().contains(a3));
    }

    @Test
    public void getAllMovies() throws Exception {
        movies.insertMovie(a);
        movies.insertMovie(b);
        movies.insertMovie(c);
        Assert.assertEquals(movies.getMovieById(a.getId()), a);
        Assert.assertEquals(movies.getMovieById(b.getId()), b);
        Assert.assertEquals(movies.getMovieById(c.getId()), c);
    }

    @Test
    public void removeMovieById() throws Exception {
        movies.insertMovie(a);
        movies.insertMovie(b);
        movies.insertMovie(c);
        Assert.assertEquals(movies.getMovieById(a.getId()), a);
        Assert.assertEquals(movies.getMovieById(b.getId()), b);
        Assert.assertEquals(movies.getMovieById(c.getId()), c);
        movies.removeMovieById(c.getId());
        Assert.assertNull(movies.getMovieById(c.getId()));
    }

    @Test
    public void updateMovie() throws Exception {
        movies.insertMovie(a);
        movies.insertMovie(b);
        movies.insertMovie(c);
        c.setId(b.getId());
        movies.updateMovie(c);
        Assert.assertEquals(movies.getMovieById(b.getId()), movies.getMovieById(c.getId()));
    }

    @Test
    public void insertMovie() throws Exception {
        movies.insertMovie(a);
        Assert.assertTrue(movies.getAllMovies().contains(a));
        Assert.assertFalse(movies.getAllMovies().contains(b));
    }

    @Test
    public void insertActorsToMovie() throws Exception {
        movies.insertMovie(b);
        LinkedHashSet<Actor> list = new LinkedHashSet<>();
        list.add(a1);
        list.add(a2);
        movies.insertActorsToMovie(list, b.getId());
    }

}