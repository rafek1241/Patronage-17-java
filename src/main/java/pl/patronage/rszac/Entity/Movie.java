package pl.patronage.rszac.entity;

import org.springframework.beans.factory.annotation.Autowired;
import pl.patronage.rszac.dao.PriceCatDao;

import java.util.LinkedHashSet;
import java.util.Set;

public class Movie {
    private int id;
    private String name;
    private Set<Actor> actors;
    private int category;
    private boolean rented;
    @Autowired
    private static PriceCatDao priceCatDao = new PriceCatDao();

    //Define non arg constructor otherwise error 400:Bad request to POST and PUT actions
    public Movie() {
    }

    public Movie(int id, String name, int CatId, Set<Actor> list) {
        this.id = id;
        this.name = name;
        this.rented = false;
        this.actors = list;
        this.category = CatId;
    }

    public Movie(int id, String name, int CatId) {
        this.id = id;
        this.name = name;
        this.rented = false;
        this.category = CatId;
        this.actors = new LinkedHashSet<>();
    }

    public PriceCategory getCategory() {
        return priceCatDao.getCategoryById(category);
    }

    public void setCategory(int category) {
        this.category = category;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Movie)) return false;

        Movie movie = (Movie) o;

        if (getId() != movie.getId()) return false;
        if (isRented() != movie.isRented()) return false;
        if (getName() != null ? !getName().equals(movie.getName()) : movie.getName() != null) return false;
        if (getActors() != null ? !getActors().equals(movie.getActors()) : movie.getActors() != null) return false;
        return getCategory() != null ? getCategory().equals(movie.getCategory()) : movie.getCategory() == null;
    }

    @Override
    public int hashCode() {
        int result = getId();
        result = 31 * result + (getName() != null ? getName().hashCode() : 0);
        result = 31 * result + (getActors() != null ? getActors().hashCode() : 0);
        result = 31 * result + (getCategory() != null ? getCategory().hashCode() : 0);
        result = 31 * result + (isRented() ? 1 : 0);
        return result;
    }

    public Set<Actor> getActors() {
        return actors;
    }

    public void setActors(Set<Actor> list) {
        this.actors = list;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isRented() {
        return rented;
    }

    public void setRented(boolean rented) {
        this.rented = rented;
    }
}
