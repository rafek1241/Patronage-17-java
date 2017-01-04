package pl.patronage.rszac.Dao;

import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import pl.patronage.rszac.Entity.Actor;

import java.util.Iterator;

public class ActorDaoTest {

    @Autowired
    ActorDao actors = new ActorDao();

    @After
    public void tearDown() throws Exception {
        //ta metoda będzie wywołana po każdym teście
        //ISSUE: error with that my actors list are filled with id:2 actor. ALWAYS!! :/
        if (actors.getAllActors().size() != 0) {
            Iterator<Actor> it = actors.getAllActors().iterator();
            while (it.hasNext()) {
                Actor akt = it.next();
                it.remove();
            }

        }
    }

    @Test
    public void getAllActors() throws Exception {
        actors = new ActorDao();
        Actor test = new Actor(1, "ActorName A", "ActorSurname A");
        Actor test2 = new Actor(2, "ActorName B", "ActorSurname B");
        Assert.assertTrue(actors.getAllActors().isEmpty());
        actors.insertActor(test);
        Assert.assertEquals(1, actors.getAllActors().size());
        actors.insertActor(test2);
        Assert.assertEquals(2, actors.getAllActors().size());

    }

    @Test
    public void getActorById() throws Exception {
        Actor test = new Actor(1, "ActorName A", "ActorSurname A");
        Actor test2 = new Actor(2, "ActorName B", "ActorSurname B");
        actors.insertActor(test);
        actors.insertActor(test2);
        Assert.assertEquals(2, actors.getAllActors().size());
        Assert.assertEquals(actors.getActorById(test.getId()), test);
        Assert.assertEquals(actors.getActorById(test2.getId()), test2);
        actors.removeActorById(test.getId());
        Assert.assertNull(actors.getActorById(test.getId()));
    }

    @Test
    public void removeActorById() throws Exception {

        Actor test = new Actor(1, "ActorName A", "ActorSurname A");
        Actor test2 = new Actor(2, "ActorName B", "ActorSurname B");
        actors.insertActor(test);
        actors.insertActor(test2);

        Assert.assertEquals(2, actors.getAllActors().size());
        actors.removeActorById(test.getId());
        Assert.assertNotEquals(2, actors.getAllActors().size());
        Assert.assertNull(actors.getActorById(test.getId()));
    }

    @Test
    public void updateActor() throws Exception {
        Actor test = new Actor(5, "ActorName A", "ActorSurname A");
        Actor test2 = new Actor(5, "ActorName B", "ActorSurname B");
        actors.insertActor(test);
        Assert.assertEquals(5, test.getId());
        Assert.assertEquals(actors.getActorById(test.getId()), test);
        actors.updateActor(test2);
        Assert.assertNotEquals(actors.getActorById(test.getId()).getName(), new Actor(5, "ActorName A", "ActorSurname A").getName());
        Assert.assertEquals(actors.getActorById(test.getId()), test);
    }

    @Test
    public void insertActor() throws Exception {
        Actor test = new Actor(5, "ActorName A", "ActorSurname A");
        actors.insertActor(test);
        Assert.assertEquals(actors.getActorById(test.getId()), test);
        Assert.assertEquals(1, actors.getAllActors().size());
        actors.insertActor(test);
        Assert.assertEquals(actors.getActorById(test.getId()), test);
        Assert.assertEquals(1, actors.getAllActors().size());

    }

}