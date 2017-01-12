package pl.patronage.rszac.entity;


import java.math.BigDecimal;

public class PriceCategory {
    private int id;
    private String name;
    private BigDecimal price;

    public PriceCategory(int id, String name, int price) {
        this.id = id;
        this.name = name;
        this.price = BigDecimal.valueOf(price);
    }

    //Define non arg constructor otherwise error 400:Bad request to POST and PUT actions
    public PriceCategory() {
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

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
