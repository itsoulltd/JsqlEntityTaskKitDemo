package com.infoworks.lab.dpatterns.decorator.mypizza;

import java.math.BigDecimal;

public class ThinCrustPizza implements Pizza{
    protected String description;
    public String getDescription(){
        return description;
    }

    public ThinCrustPizza(){
        super();
        this.description = "Thin Crust Pizza";
    }

    @Override
    public BigDecimal getCost() {
        return new BigDecimal(12.00);
    }
}
