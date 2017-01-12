package pl.patronage.rszac.dao;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import pl.patronage.rszac.entity.Actor;
import pl.patronage.rszac.entity.Movie;

import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

public class MovieDaoTest {
    @Autowired
    MovieDao movieDao;
    @Autowired
    ActorDao actorDao;
    @Autowired
    PriceCatDao priceCatDao;
    Movie a, b, c;
    Actor a1, a2, a3;

    @Before
    public void setUp() throws Exception {
        //ta metoda będzie wywołana przed każdym testem
        priceCatDao = new PriceCatDao();
        movieDao = new MovieDao();
        actorDao = new ActorDao();
        Set<Actor> list = new LinkedHashSet<>();
        a1 = new Actor(1, "Jan", "Kowalski");
        a2 = new Actor(2, "Barbara", "Kowalska");
        a3 = new Actor(3, "Konstanty", "Galczynski");
        list.add(a1);
        list.add(a2);
        a = new Movie(1, "Movie A", priceCatDao.getCategoryById(1));
        b = new Movie(2, "Movie B", priceCatDao.getCategoryById(2));
        c = new Movie(3, "Movie C", priceCatDao.getCategoryById(3), list);
    }


    @After
    public void tearDown() throws Exception {
        if (movieDao.getAllMovies().size() != 0) {
            Iterator<Movie> it = movieDao.getAllMovies().iterator();
            while (it.hasNext()) {
                it.next();
                it.remove();
            }

        }

    }

    @Test
    public void getMovieById() throws Exception {
        movieDao.insertMovie(a);
        movieDao.insertMovie(c);
        Assert.assertEquals(movieDao.getMovieById(c.getId()), c);
        Assert.assertEquals(movieDao.getMovieById(a.getId()), a);
        Assert.assertNull(movieDao.getMovieById(b.getId()));
    }

    @Test
    public void setActorToMovieById() throws Exception {
        actorDao.insertActor(a1);
        actorDao.insertActor(a2);
        movieDao.insertMovie(a);
        movieDao.setActorToMovieById(a1.getId(), a.getId());
        movieDao.setActorToMovieById(a2.getId(), a.getId());
        Assert.assertTrue(movieDao.getMovieById(a.getId()).getActors().contains(a1));
        Assert.assertTrue(movieDao.getMovieById(a.getId()).getActors().contains(a2));
        Assert.assertFalse(movieDao.getMovieById(a.getId()).getActors().contains(a3));
    }

    @Test
    public void remActorFromMovieById() throws Exception {
        actorDao.insertActor(a1);
        actorDao.insertActor(a2);
        actorDao.insertActor(a3);
        movieDao.insertMovie(c);
        movieDao.remActorFromMovieById(a2.getId(), c.getId());
        movieDao.remActorFromMovieById(a1.getId(), c.getId());
        Assert.assertFalse(movieDao.getMovieById(c.getId()).getActors().contains(a1));
        Assert.assertFalse(movieDao.getMovieById(c.getId()).getActors().contains(a2));
        Assert.assertFalse(movieDao.getMovieById(c.getId()).getActors().contains(a3));
    }

    @Test
    public void getAllMovies() throws Exception {
        movieDao.insertMovie(a);
        movieDao.insertMovie(b);
        movieDao.insertMovie(c);
        Assert.assertEquals(movieDao.getMovieById(a.getId()), a);
        Assert.assertEquals(movieDao.getMovieById(b.getId()), b);
        Assert.assertEquals(movieDao.getMovieById(c.getId()), c);
    }

    @Test
    public void removeMovieById() throws Exception {
        movieDao.insertMovie(a);
        movieDao.insertMovie(b);
        movieDao.insertMovie(c);
        Assert.assertEquals(movieDao.getMovieById(a.getId()), a);
        Assert.assertEquals(movieDao.getMovieById(b.getId()), b);
        Assert.assertEquals(movieDao.getMovieById(c.getId()), c);
        movieDao.removeMovieById(c.getId());
        Assert.assertNull(movieDao.getMovieById(c.getId()));
    }

    @Test
    public void updateMovie() throws Exception {
        movieDao.insertMovie(a);
        movieDao.insertMovie(b);
        movieDao.insertMovie(c);
        c.setId(b.getId());
        movieDao.updateMovie(c);
        Assert.assertEquals(movieDao.getMovieById(b.getId()), movieDao.getMovieById(c.getId()));
    }

    @Test
    public void insertMovie() throws Exception {
        movieDao.insertMovie(a);
        Assert.assertTrue(movieDao.getAllMovies().contains(a));
        Assert.assertFalse(movieDao.getAllMovies().contains(b));
    }

    @Test
    public void insertActorsToMovie() throws Exception {
        movieDao.insertMovie(b);
        LinkedHashSet<Actor> list = new LinkedHashSet<>();
        list.add(a1);
        list.add(a2);
        movieDao.insertActorsToMovie(list, b.getId());
    }

    @Test
    public void getMovieByPriceCatId() throws Exception {
        Assert.assertNull(movieDao.getMovieByPriceCatId(4));
        Assert.assertNotNull(movieDao.getMovieByPriceCatId(3));
        Assert.assertNotNull(movieDao.getMovieByPriceCatId(1));
    }

    @Test
    public void getAllMoviesAvailable() throws Exception {

        Assert.assertNotNull(movieDao.getAllMoviesAvailable());
        Iterator<Movie> it = movieDao.getAllMoviesAvailable().iterator();
        while (it.hasNext()) {
            Movie mov = it.next();
            mov.setRented(true);
        }
        Assert.assertTrue(movieDao.getAllMoviesAvailable().isEmpty());
    }
}