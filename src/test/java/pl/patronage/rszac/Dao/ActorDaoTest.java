package pl.patronage.rszac.dao;

import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import pl.patronage.rszac.entity.Actor;

import java.util.Iterator;

public class ActorDaoTest {

    @Autowired
    ActorDao actorDao = new ActorDao();

    @After
    public void tearDown() throws Exception {
        //ta metoda będzie wywołana po każdym teście
        //ISSUE: error with that my actorDao list are filled with id:2 actor. ALWAYS!! :/
        if (actorDao.getAllActors().size() != 0) {
            Iterator<Actor> it = actorDao.getAllActors().iterator();
            while (it.hasNext()) {
                Actor akt = it.next();
                it.remove();
            }

        }
    }

    @Test
    public void getAllActors() throws Exception {
        actorDao = new ActorDao();
        Actor test = new Actor(1, "ActorName A", "ActorSurname A");
        Actor test2 = new Actor(2, "ActorName B", "ActorSurname B");
        Assert.assertTrue(actorDao.getAllActors().isEmpty());
        actorDao.insertActor(test);
        Assert.assertEquals(1, actorDao.getAllActors().size());
        actorDao.insertActor(test2);
        Assert.assertEquals(2, actorDao.getAllActors().size());

    }

    @Test
    public void getActorById() throws Exception {
        Actor test = new Actor(1, "ActorName A", "ActorSurname A");
        Actor test2 = new Actor(2, "ActorName B", "ActorSurname B");
        actorDao.insertActor(test);
        actorDao.insertActor(test2);
        Assert.assertEquals(2, actorDao.getAllActors().size());
        Assert.assertEquals(actorDao.getActorById(test.getId()), test);
        Assert.assertEquals(actorDao.getActorById(test2.getId()), test2);
        actorDao.removeActorById(test.getId());
        Assert.assertNull(actorDao.getActorById(test.getId()));
    }

    @Test
    public void removeActorById() throws Exception {
        actorDao.getAllActors().clear();
        Actor test = new Actor(1, "ActorName A", "ActorSurname A");
        Actor test2 = new Actor(2, "ActorName B", "ActorSurname B");
        actorDao.insertActor(test);
        actorDao.insertActor(test2);
        Assert.assertEquals(2, actorDao.getAllActors().size());
        actorDao.removeActorById(test.getId());
        Assert.assertNotEquals(2, actorDao.getAllActors().size());
        Assert.assertNull(actorDao.getActorById(test.getId()));
    }

    @Test
    public void updateActor() throws Exception {
        Actor test = new Actor(5, "ActorName A", "ActorSurname A");
        Actor test2 = new Actor(5, "ActorName B", "ActorSurname B");
        actorDao.insertActor(test);
        Assert.assertEquals(5, test.getId());
        Assert.assertEquals(actorDao.getActorById(test.getId()), test);
        actorDao.updateActor(test2);
        Assert.assertNotEquals(actorDao.getActorById(test.getId()).getName(), new Actor(5, "ActorName A", "ActorSurname A").getName());
        Assert.assertEquals(actorDao.getActorById(test.getId()), test);
    }

    @Test
    public void insertActor() throws Exception {
        Actor test = new Actor(5, "ActorName A", "ActorSurname A");
        actorDao.insertActor(test);
        Assert.assertEquals(actorDao.getActorById(test.getId()), test);
        Assert.assertEquals(1, actorDao.getAllActors().size());
        actorDao.insertActor(test);
        Assert.assertEquals(actorDao.getActorById(test.getId()), test);
        Assert.assertEquals(1, actorDao.getAllActors().size());

    }

}