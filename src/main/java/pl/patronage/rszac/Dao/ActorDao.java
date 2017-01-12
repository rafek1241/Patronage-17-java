package pl.patronage.rszac.dao;

import org.springframework.stereotype.Repository;
import pl.patronage.rszac.entity.Actor;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Repository
public class ActorDao {
    private static Map<Integer, Actor> actors = new HashMap<>();

    static {
        actors = new HashMap<Integer, Actor>() {


            {
                put(1, new Actor(1, "Karczek", "Kowalski"));
            }
        };
    }


    public Collection<Actor> getAllActors() {
        return actors.values();
    }


    public Actor getActorById(long id) {

        return actors.get((int) id);
    }

    public void removeActorById(long id) {
        actors.remove((int) id);
    }

    public void updateActor(Actor actor) {
        Actor a = actors.get(actor.getId());
        a.setName(actor.getName());
        a.setSurname(actor.getSurname());
        actors.put(actor.getId(), a);
    }

    public void insertActor(Actor actor) {
        actors.put(actor.getId(), actor);
    }
}