package com.infoworks.lab.dpatterns.decorator.mypizza;

import java.math.BigDecimal;

public class CheeseCrustPizza implements Pizza{
    protected String description;
    public String getDescription(){
        return description;
    }

    public CheeseCrustPizza(){
        super();
        this.description = "Cheese Crust Pizza";
    }

    @Override
    public BigDecimal getCost() {
        return new BigDecimal(20.20);
    }
}
