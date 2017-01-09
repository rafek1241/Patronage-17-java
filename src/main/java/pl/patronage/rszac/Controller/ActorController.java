package pl.patronage.rszac.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.patronage.rszac.Entity.Actor;
import pl.patronage.rszac.Service.ActorService;

import java.util.Collection;

@RestController
@RequestMapping("/actors")
public class ActorController {
    @Autowired
    private ActorService actorService;

    @RequestMapping(method = RequestMethod.GET)
    public Collection<Actor> getAllActors() {
        return actorService.getAllActors();

    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Actor getActorById(@PathVariable("id") long id) {
        return this.actorService.getActorById(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void removeActorById(@PathVariable("id") long id) {
        actorService.removeActorById(id);
    }

    @RequestMapping(method = RequestMethod.PUT, consumes = org.springframework.http.MediaType.APPLICATION_JSON_VALUE)
    public void updateActor(@RequestBody Actor actor) {
        actorService.updateActor(actor);
    }

    @RequestMapping(method = RequestMethod.POST, consumes = org.springframework.http.MediaType.APPLICATION_JSON_VALUE)
    public void insertActor(@RequestBody Actor actor) {
        actorService.insertActor(actor);
    }

}
