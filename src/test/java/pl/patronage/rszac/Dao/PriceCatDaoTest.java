package pl.patronage.rszac.dao;

import org.junit.Assert;
import org.junit.Test;

public class PriceCatDaoTest {

    PriceCatDao priceCatDao = new PriceCatDao();

    @Test
    public void getListOfCategories() throws Exception {
        Assert.assertNotNull(priceCatDao.getListOfCategories());
    }

    @Test
    public void getCategoryById() throws Exception {
        Assert.assertNotNull(priceCatDao.getCategoryById(1));
        Assert.assertNotNull(priceCatDao.getCategoryById(2));
        Assert.assertNotNull(priceCatDao.getCategoryById(3));
        Assert.assertTrue(priceCatDao.getListOfCategories().contains(priceCatDao.getCategoryById(1)));
    }
}
