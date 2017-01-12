package pl.patronage.rszac.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.patronage.rszac.dao.UserDao;
import pl.patronage.rszac.entity.Movie;
import pl.patronage.rszac.entity.User;

import java.util.Collection;
import java.util.HashSet;

@Service
public class UserService {
    @Autowired
    private UserDao userDao;


    public Collection<User> getAllUsers() {
        return this.userDao.getAllUsers();
    }

    public boolean createUser(User usr) {
        return this.userDao.createUser(usr);
    }

    public Collection<Movie> getListOfRentedMovies(int userId) {
        return this.userDao.getListOfRentedMovies(userId);
    }

    public boolean rentMovie(int userId, HashSet<Integer> movieId) {
        return this.userDao.rentMovie(userId, movieId);
    }

    public boolean returnMovie(int userId, HashSet<Integer> moviesId) {
        return this.userDao.returnMovie(userId, moviesId);
    }

    public boolean pay(int id, double payment) {
        return this.userDao.pay(id, payment);
    }
}
