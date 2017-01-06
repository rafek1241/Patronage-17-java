package pl.patronage.rszac.Dao;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import pl.patronage.rszac.Entity.Actor;
import pl.patronage.rszac.Entity.Movie;
import pl.patronage.rszac.Entity.User;

import java.util.*;

public class UserDaoTest {
    @Autowired
    MovieDao movies;
    @Autowired
    ActorDao actors;
    @Autowired
    PriceCatDao priceCategory;
    @Autowired
    UserDao users;
    Movie m1, m2, m3;
    Actor a1, a2, a3;
    User u1, u2, u3;

    @Before
    public void setUp() throws Exception {
        //ta metoda będzie wywołana przed każdym testem
        movies = new MovieDao();
        actors = new ActorDao();
        priceCategory = new PriceCatDao();
        users = new UserDao();
        Set<Movie> listM = new HashSet<>();

        Set<Actor> list = new LinkedHashSet<>();

        a1 = new Actor(1, "Jan", "Kowalski");
        a2 = new Actor(2, "Barbara", "Kowalska");
        a3 = new Actor(3, "Konstanty", "Galczynski");

        list.add(a1);
        list.add(a2);

        m1 = new Movie(31, "Movie A", priceCategory.getCategoryById(1));
        m2 = new Movie(32, "Movie B", priceCategory.getCategoryById(2));
        m3 = new Movie(33, "Movie C", priceCategory.getCategoryById(3), list);

        m2.setRented(true);
        m3.setRented(true);
        listM.add(m2);
        listM.add(m3);

        u1 = new User(1, "login1", "password1", "Jan", "Kowalski");
        u2 = new User(2, "login2", "password2", "Janina", "Kowalska");
        u3 = new User(3, "login3", "password3", "Barbara", "Nowacka");

        actors.insertActor(a1);
        actors.insertActor(a2);
        actors.insertActor(a3);

        movies.insertMovie(m1);
        movies.insertMovie(m2);
        movies.insertMovie(m3);

        users.createUser(u1);
        users.createUser(u2);
    }

    @After
    public void tearDown() throws Exception {

    }


    @Test
    public void getAllUsers() throws Exception {

        Assert.assertNotNull(users.getAllUsers());
        users.getAllUsers().clear();
        Assert.assertTrue(users.getAllUsers().isEmpty());

    }

    @Test
    public void createUser() throws Exception {

        Assert.assertTrue(users.createUser(u3));
        Assert.assertFalse(users.createUser(u3));

        Assert.assertFalse(users.createUser(u1));
    }

    @Test
    public void getListOfRentedMovies() throws Exception {
        Assert.assertNotNull(users.getListOfRentedMovies(u2.getId()));
        Assert.assertTrue(users.getListOfRentedMovies(u1.getId()).isEmpty());
    }

    private void returnAllMovies(Collection<Movie> t, User usr) {
        usr.setRentedMovies(new HashSet<Movie>());
        Iterator<Movie> i = t.iterator();
        while (i.hasNext()) {
            Movie mov = i.next();
            if (mov.isRented())
                mov.setRented(false);
        }

    }

    @Test
    public void rentMovie() throws Exception {
        int idnotUser = 4;
        HashSet<Integer> listIdMovies = new HashSet<>();
        Assert.assertFalse(users.rentMovie(u1.getId(), listIdMovies));
        listIdMovies.add(31);
        listIdMovies.add(32);
        listIdMovies.add(33);

        Movie m4 = new Movie(34, "Movie A2", priceCategory.getCategoryById(1));
        Movie m5 = new Movie(35, "Movie B2", priceCategory.getCategoryById(2));
        movies.insertMovie(m4);
        movies.insertMovie(m5);

        m5 = new Movie(36, "Movie B2", priceCategory.getCategoryById(2));
        movies.insertMovie(m5);
        m5 = new Movie(37, "Movie B2", priceCategory.getCategoryById(2));
        movies.insertMovie(m5);
        m5 = new Movie(38, "Movie B2", priceCategory.getCategoryById(2));
        movies.insertMovie(m5);
        m5 = new Movie(39, "Movie B2", priceCategory.getCategoryById(2));
        movies.insertMovie(m5);
        m5 = new Movie(40, "Movie B2", priceCategory.getCategoryById(2));
        movies.insertMovie(m5);
        m5 = new Movie(41, "Movie B2", priceCategory.getCategoryById(2));
        movies.insertMovie(m5);
        m5 = new Movie(42, "Movie B2", priceCategory.getCategoryById(2));
        movies.insertMovie(m5);
        m5 = new Movie(43, "Movie B2", priceCategory.getCategoryById(2));
        movies.insertMovie(m5);
        m5 = new Movie(44, "Movie B2", priceCategory.getCategoryById(2));
        movies.insertMovie(m5);
        m5 = new Movie(45, "Movie B2", priceCategory.getCategoryById(3));
        movies.insertMovie(m5);

        //for this test set all prices equally
        priceCategory.getCategoryById(1).setPrice(10);
        priceCategory.getCategoryById(2).setPrice(10);
        priceCategory.getCategoryById(3).setPrice(10);


        this.returnAllMovies(movies.getAllMovies(), u1);

        Assert.assertFalse(users.rentMovie(idnotUser, listIdMovies));
        listIdMovies.add(58);
        Assert.assertFalse(users.rentMovie(u1.getId(), listIdMovies));
        listIdMovies.remove(58);
        //without discount and freemovie
        Assert.assertTrue(users.rentMovie(u1.getId(), listIdMovies));
        System.out.println("Count of rented movies: " + u1.getRentedMovies().size() + "\nUser balance: " + u1.getBalance());
        this.returnAllMovies(movies.getAllMovies(), u1);

        u1.setBalance(0);
        //with discount
        listIdMovies.remove(33);
        listIdMovies.add(34);
        Assert.assertTrue(users.rentMovie(u1.getId(), listIdMovies));
        System.out.println("Count of rented movies: " + u1.getRentedMovies().size() + "\nUser balance(discount): " + u1.getBalance());
        this.returnAllMovies(movies.getAllMovies(), u1);

        u1.setBalance(0);
        listIdMovies.remove(34);
        //with freemovie
        listIdMovies.add(35);
        listIdMovies.add(45);
        Assert.assertTrue(users.rentMovie(u1.getId(), listIdMovies));
        System.out.println("Count of rented movies: " + u1.getRentedMovies().size() + "\nUser balance(free movie): " + u1.getBalance());
        this.returnAllMovies(movies.getAllMovies(), u1);

        u1.setBalance(0);
        listIdMovies.remove(35);

        //with freemovie and discount
        listIdMovies.add(34);
        Assert.assertTrue(users.rentMovie(u1.getId(), listIdMovies));
        System.out.println("Count of rented movies: " + u1.getRentedMovies().size() + "\nUser balance(free movie and discount): " + u1.getBalance());
        this.returnAllMovies(movies.getAllMovies(), u1);

        u1.setBalance(0);


        listIdMovies.add(35);
        listIdMovies.add(36);
        listIdMovies.add(37);
        listIdMovies.add(38);
        listIdMovies.add(39);
        listIdMovies.add(40);
        listIdMovies.add(41);
        listIdMovies.add(42);
        listIdMovies.add(43);
        listIdMovies.add(44);
        listIdMovies.add(45);

        Assert.assertFalse(users.returnMovie(u1.getId(), listIdMovies));

    }

    @Test
    public void returnMovie() throws Exception {
        int idnotUser = 4;
        HashSet<Integer> listIdMovies = new HashSet<>();
        listIdMovies.add(m1.getId());
        Assert.assertFalse(users.returnMovie(u2.getId(), listIdMovies));
        listIdMovies.remove(m1.getId());
        listIdMovies.add(m2.getId());
        listIdMovies.add(m3.getId());
        m2.setRented(false);
        m3.setRented(false);
        users.rentMovie(u2.getId(), listIdMovies);
        Assert.assertFalse(users.returnMovie(idnotUser, listIdMovies));
        Assert.assertTrue(users.returnMovie(u2.getId(), listIdMovies));

    }

    @Test
    public void pay() throws Exception {
        u1.setBalance(100);
        System.out.println("User balance: " + u1.getBalance());
        Assert.assertFalse(users.pay(u1.getId(), 101));
        System.out.println("User balance: " + u1.getBalance());
        Assert.assertTrue(users.pay(u1.getId(), 99));
        System.out.println("User balance: " + u1.getBalance());
        Assert.assertFalse(users.pay(u1.getId(), 2));
        System.out.println("User balance: " + u1.getBalance());
        Assert.assertTrue(users.pay(u1.getId(), 1));
        System.out.println("User balance: " + u1.getBalance());

    }
}
