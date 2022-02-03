package com.infoworks.lab.dpatterns.decorator.decor;

import com.infoworks.lab.dpatterns.decorator.mypizza.Pizza;

import java.math.BigDecimal;

public class Pepperoni implements Toppings {
    private Pizza pizza;
    public Pepperoni(Pizza pizza){
        super();
        this.pizza = pizza;
    }

    @Override
    public void setPizza(Pizza pizza) {
        this.pizza = pizza;
    }

    @Override
    public String getDescription() {
        return this.pizza.getDescription() + " + pepperoni";
    }

    @Override
    public BigDecimal getCost() {
        return (new BigDecimal(1.50)).add(this.pizza.getCost());
    }
}
