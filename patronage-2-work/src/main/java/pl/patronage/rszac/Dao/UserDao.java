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
    MovieDao movDao;
    @Autowired
    PriceCatDao catDao;


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
        int counter = moviesId.size();
        int discount = 0, freemovie = 0;
        double total = 0;
        int discountvalue = 25;//discount % if 2 movies of first category
        Iterator<Integer> iter = moviesId.iterator();
        if (counter == 0) {
            return false;
        }
        while (iter.hasNext()) {
            int id = iter.next();
            Movie mov = movDao.getMovieById(id);
            if (mov == null || mov.isRented()) {
                return false;
            }
            if (mov.getCategory().getId() == 1)
                discount++;
            if (counter >= 4) {
                if (mov.getCategory().getId() == 3) {
                    freemovie = 1;
                }
            }
            mov.setRented(true);
            listUsers.get(userId).rentMovie(mov);
            total = total + mov.getCategory().getPrice();
        }
        if (freemovie == 1)
            total = total - catDao.getCategoryById(3).getPrice();
        if (discount >= 2)
            total = total * 0.75;
        listUsers.get(userId).setBalance(listUsers.get(userId).getBalance() + total);
        return true;
    }
}
