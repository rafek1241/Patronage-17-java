package pl.patronage.rszac.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.patronage.rszac.dao.ActorDao;
import pl.patronage.rszac.entity.Actor;

import java.util.Collection;

@Service
public class ActorService {
    @Autowired
    private ActorDao actorDao;

    public Collection<Actor> getAllActors() {
        return this.actorDao.getAllActors();

    }

    public Actor getActorById(long id) {
        return this.actorDao.getActorById(id);
    }

    public void removeActorById(long id) {
        actorDao.removeActorById(id);
    }

    public void updateActor(Actor actor) {
        this.actorDao.updateActor(actor);
    }

    public void insertActor(Actor actor) {
        this.actorDao.insertActor(actor);
    }
}
