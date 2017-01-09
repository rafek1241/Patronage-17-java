package pl.patronage.rszac.Dao;

import org.junit.Assert;
import org.junit.Test;

public class PriceCatDaoTest {
    PriceCatDao tester = new PriceCatDao();

    @Test
    public void getListOfCategories() throws Exception {
        Assert.assertNotNull(tester.getListOfCategories());
    }

    @Test
    public void getCategoryById() throws Exception {
        Assert.assertNotNull(tester.getCategoryById(1));
        Assert.assertNotNull(tester.getCategoryById(2));
        Assert.assertNotNull(tester.getCategoryById(3));
        Assert.assertTrue(tester.getListOfCategories().contains(tester.getCategoryById(1)));
    }
}
