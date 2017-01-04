package pl.patronage.rszac.Entity;

import java.util.LinkedHashSet;
import java.util.Set;



public class Movie {
    private int id;
    private String name;
    private Set<Actor> actors;

    public Movie(int id, String name, Set<Actor> list) {
        this.id = id;
        this.name = name;
        this.actors = list;
    }

    public Movie(int id, String name) {
        this.id = id;
        this.name = name;
        this.actors = new LinkedHashSet<>();
    }

    public void setActors(Set<Actor> list) {
        this.actors = list;
    }


    public Set<Actor> getActors() {
        return actors;
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

}
