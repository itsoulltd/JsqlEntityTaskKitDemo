package com.infoworks.lab.dpatterns.decorator.mypizza;

import java.math.BigDecimal;

/**
 * This is a concrete component, we will decorate with various decorator
 * e.g. Pepperoni, Cheese, Mushroom, Sausage etc
 */
public class ThickCrustPizza implements Pizza{
    protected String description;
    public String getDescription(){
        return description;
    }

    public ThickCrustPizza(){
        super();
        this.description = "Thick Crust Pizza";
    }

    @Override
    public BigDecimal getCost() {
        return new BigDecimal(15.00);
    }
}
