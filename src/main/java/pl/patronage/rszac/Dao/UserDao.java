package pl.patronage.rszac.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pl.patronage.rszac.entity.Movie;
import pl.patronage.rszac.entity.User;

import java.math.BigDecimal;
import java.util.*;

@Repository
public class UserDao {
    private static Map<Integer, User> listUsers = new HashMap<>();
    @Autowired
    MovieDao movieDao = new MovieDao();
    @Autowired
    PriceCatDao priceCatDao = new PriceCatDao();


    public Collection<User> getAllUsers() {
        return listUsers.values();
    }

    public boolean createUser(User user) {
        if (listUsers.values().stream().filter(user1 -> user1.equals(user)).findAny().isPresent()) {
            return false;
        }
        listUsers.put(listUsers.size() + 1, user);
        return true;
    }

    public Collection<Movie> getListOfRentedMovies(int userId) {
        return listUsers.get(userId).getRentedMovies();
    }

    public boolean rentMovie(int userId, HashSet<Integer> moviesId) {

        if (listUsers.get(userId) == null) {
            return false;
        }
        int counter = moviesId.size(), counterUserMovs = listUsers.get(userId).getRentedMovies().size();
        if (counter == 0) {
            return false;
        }
        if ((counter + counterUserMovs) > 10) {
            return false;
        }
        int discount = 0, freemovie = 0;
        double total = 0;
        double discountvalue = 0.75;
        Set<Movie> movs = new HashSet<>();
        moviesId.forEach(integer -> movs.add(movieDao.getMovieById(integer)));
        if (movs.stream().anyMatch(movie -> movie == null)) {
            return false;
        }
        if (movs.stream().filter(movie -> movie.isRented()).findFirst().isPresent()) {
            return false;
        }
        for (Integer id : moviesId) {
            Movie mov = movieDao.getMovieById(id);
            if (mov.getCategory().getId() == 1)
                discount++;
            if (mov.getCategory().getId() == 3) {
                freemovie = 1;
            }
            mov.setRented(true);
            listUsers.get(userId).rentMovie(mov);
            total = total + mov.getCategory().getPrice().doubleValue();
        }
        if (freemovie == 1 && counter >= 4) {
            total = total - priceCatDao.getCategoryById(3).getPrice().doubleValue();
        }
        if (discount >= 2) {
            total = total * discountvalue;
        }
        total = (double) Math.round(total * 100.0) / 100.0;
        listUsers.get(userId).setBalance(BigDecimal.valueOf(listUsers.get(userId).getBalance() + total));
        return true;
    }

    public boolean returnMovie(int userId, HashSet<Integer> moviesId) {
        Iterator<Integer> it = moviesId.iterator();
        if (!listUsers.containsKey(userId)) {
            return false;
        }
        Set<Movie> movs = listUsers.get(userId).getRentedMovies();
        while (it.hasNext()) {
            Movie mov = movieDao.getMovieById(it.next());
            if (mov == null) {
                return false;
            }
            if (movs.contains(mov)) {
                movs.remove(mov);
            } else {
                return false;
            }
            mov.setRented(false);
        }

        listUsers.get(userId).setRentedMovies(movs);
        return true;
    }

    public boolean pay(int id, double payment) {
        if (payment > listUsers.get(id).getBalance()) {
            return false;
        } else {
            listUsers.get(id).setBalance(BigDecimal.valueOf(listUsers.get(id).getBalance() - payment));
        }
        return true;
    }
}
