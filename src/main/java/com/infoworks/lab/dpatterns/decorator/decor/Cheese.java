package com.infoworks.lab.dpatterns.decorator.decor;

import com.infoworks.lab.dpatterns.decorator.mypizza.Pizza;

import java.math.BigDecimal;

public class Cheese implements Toppings {
    private Pizza pizza;
    public Cheese(Pizza pizza){
        super();
        this.pizza = pizza;
    }

    @Override
    public void setPizza(Pizza pizza) {
        this.pizza = pizza;
    }

    @Override
    public String getDescription() {
        return this.pizza.getDescription() + " + cheese";
    }

    @Override
    public BigDecimal getCost() {
        return (new BigDecimal(1.0)).add(this.pizza.getCost());
    }
}
