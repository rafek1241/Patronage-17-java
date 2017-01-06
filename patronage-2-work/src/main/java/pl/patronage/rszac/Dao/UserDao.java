package pl.patronage.rszac.Dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pl.patronage.rszac.Entity.Movie;
import pl.patronage.rszac.Entity.User;

import java.util.*;

@Repository
public class UserDao {
    private static Map<Integer, User> listUsers = new HashMap<>();
    @Autowired
    MovieDao movDao = new MovieDao();
    @Autowired
    PriceCatDao catDao = new PriceCatDao();


    public Collection<User> getAllUsers() {
        return listUsers.values();
    }

    public boolean createUser(User user) {
        Iterator<User> it = listUsers.values().iterator();
        while (it.hasNext()) {
            User us = it.next();
            if (us.equals(user)) {
                return false;
            }
        }

        listUsers.put(listUsers.size() + 1, user);
        return true;
    }

    public Collection<Movie> getListOfRentedMovies(int userId) {
        return listUsers.get(userId).getRentedMovies();
    }

    public boolean rentMovie(int userId, HashSet<Integer> moviesId) {
        if (listUsers.get(userId) == null)
            return false;
        int counter = moviesId.size(), counterUserMovs = listUsers.get(userId).getRentedMovies().size();
        if (counter == 0) {
            return false;
        }
        if ((counter + counterUserMovs) > 10)
            return false;

        int discount = 0, freemovie = 0;
        double total = 0;
        double discountvalue = 0.75;//discount % if 2 movies of first category
        Iterator<Integer> iter = moviesId.iterator();


        while (iter.hasNext()) {
            int id = iter.next();
            Movie mov = movDao.getMovieById(id);
            if (mov == null || mov.isRented()) {
                return false;
            }
        }

        iter = moviesId.iterator();


        while (iter.hasNext()) {
            int id = iter.next();
            Movie mov = movDao.getMovieById(id);
            if (mov.getCategory().getId() == 1)
                discount++;
            if (mov.getCategory().getId() == 3) {
                freemovie = 1;
            }
            mov.setRented(true);
            listUsers.get(userId).rentMovie(mov);
            total = total + mov.getCategory().getPrice();
        }
        if (freemovie == 1 && counter >= 4)
            total = total - catDao.getCategoryById(3).getPrice();
        if (discount >= 2)
            total = total * discountvalue;

        //Round to 0.00
        //         --^
        total = (double) Math.round(total * 100.0) / 100.0;
        listUsers.get(userId).setBalance(listUsers.get(userId).getBalance() + total);
        return true;
    }

    public boolean returnMovie(int userId, HashSet<Integer> moviesId) {
        Iterator<Integer> it = moviesId.iterator();
        if (!listUsers.containsKey(userId))
            return false;
        Set<Movie> movs = listUsers.get(userId).getRentedMovies();
        while (it.hasNext()) {
            Movie mov = movDao.getMovieById(it.next());
            if (mov == null)
                return false;
            if (movs.contains(mov)) {
                movs.remove(mov);
            } else
                return false;
            mov.setRented(false);
        }

        listUsers.get(userId).setRentedMovies(movs);
        return true;
    }

    public boolean pay(int id, double payment) {
        if (payment > listUsers.get(id).getBalance()) {
            return false;
        } else
            listUsers.get(id).setBalance(listUsers.get(id).getBalance() - payment);
        return true;
    }
}
