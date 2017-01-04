package pl.patronage.rszac.Entity;


import java.util.HashSet;
import java.util.Set;

public class User {
    private int id;
    private String login, password, name, surname;
    private Set<Movie> rentedMovies;
    private double balance;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;

        User user = (User) o;

        if (login != null ? !login.equals(user.login) : user.login != null) return false;
        if (password != null ? !password.equals(user.password) : user.password != null) return false;
        if (name != null ? !name.equals(user.name) : user.name != null) return false;
        return surname != null ? surname.equals(user.surname) : user.surname == null;
    }


    public User(int id, String login, String password, String name, String surname, Set<Movie> rentedMovies) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.rentedMovies = rentedMovies;
        this.balance = 0;
    }

    public User(int id, String login, String password, String name, String surname) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.rentedMovies = new HashSet<>();
        this.balance = 0;
    }

    public int getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Movie> getRentedMovies() {
        return rentedMovies;
    }

    public void rentMovie(Movie mov) {
        this.rentedMovies.add(mov);
    }

    public void returnMovie(Movie mov) {
        this.rentedMovies.remove(mov);
    }

    public void setRentedMovies(Set<Movie> rentedMovies) {
        this.rentedMovies = rentedMovies;
    }

    //Define non arg constructor otherwise error 400:Bad request to POST and PUT actions
    public User() {
        this.balance = 0;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}
