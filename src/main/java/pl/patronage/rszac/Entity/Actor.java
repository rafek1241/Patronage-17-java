package pl.patronage.rszac.entity;


public class Actor {
    private int id;
    private String name;
    private String surname;


    public Actor(int id, String name, String surname) {
        this.id = id;
        this.name = name;
        this.surname = surname;
    }

    //Define non arg constructor otherwise error 400:Bad request to POST and PUT actions
    public Actor() {
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }
}
