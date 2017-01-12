package pl.patronage.rszac.dao;

import org.springframework.stereotype.Repository;
import pl.patronage.rszac.entity.PriceCategory;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Repository
public class PriceCatDao {
    private static Map<Integer, PriceCategory> listOfCategories;


    static {
        listOfCategories = new HashMap<Integer, PriceCategory>() {
            {
                put(1, new PriceCategory(1, "Nowości", 35));
                put(2, new PriceCategory(2, "Hity", 20));
                put(3, new PriceCategory(3, "Pozostałe", 10));
            }

        };
    }

    public Collection<PriceCategory> getListOfCategories() {
        return listOfCategories.values();
    }

    public PriceCategory getCategoryById(int id) {
        return listOfCategories.get(id);
    }

}
