package pl.patronage.rszac.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.patronage.rszac.Entity.Movie;
import pl.patronage.rszac.Entity.User;
import pl.patronage.rszac.Service.UserService;

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
}
