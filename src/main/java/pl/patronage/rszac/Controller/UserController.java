package pl.patronage.rszac.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import pl.patronage.rszac.entity.Movie;
import pl.patronage.rszac.entity.User;
import pl.patronage.rszac.service.UserService;

import java.util.Collection;
import java.util.HashSet;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    UserService userService;

    @RequestMapping(method = RequestMethod.GET)
    public Collection<User> getAllUsers() {
        return this.userService.getAllUsers();

    }

    @RequestMapping(method = RequestMethod.POST, consumes = org.springframework.http.MediaType.APPLICATION_JSON_VALUE)
    public boolean createUser(@RequestBody User usr) {
        return userService.createUser(usr);
    }

    @RequestMapping(value = "/{idU}/rent", method = RequestMethod.GET)
    public Collection<Movie> getListOfRentedMovies(@PathVariable("idU") int userId) {
        return this.userService.getListOfRentedMovies(userId);
    }

    @RequestMapping(value = "/{idU}/rent", method = RequestMethod.POST, consumes = org.springframework.http.MediaType.APPLICATION_JSON_VALUE)
    public boolean rentMovie(@PathVariable("idU") int userId, @RequestBody HashSet<Integer> moviesId) {
        return this.userService.rentMovie(userId, moviesId);
    }

    @RequestMapping(value = "/{idU}/rent", method = RequestMethod.DELETE, consumes = org.springframework.http.MediaType.APPLICATION_JSON_VALUE)
    public boolean returnMovie(@PathVariable("idU") int userId, @RequestBody HashSet<Integer> moviesId) {
        return this.userService.returnMovie(userId, moviesId);
    }

    @RequestMapping(value = "/{id}/pay", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public boolean setUserBalance(@PathVariable("id") int id, @RequestBody double payment) {
        return this.userService.pay(id, payment);
    }

}
