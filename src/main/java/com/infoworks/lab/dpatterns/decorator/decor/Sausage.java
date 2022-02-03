package com.infoworks.lab.dpatterns.decorator.decor;

import com.infoworks.lab.dpatterns.decorator.mypizza.Pizza;

import java.math.BigDecimal;

public class Sausage implements Toppings {
    private Pizza pizza;
    public Sausage(Pizza pizza){
        super();
        this.pizza = pizza;
    }

    @Override
    public void setPizza(Pizza pizza) {
        this.pizza = pizza;
    }

    @Override
    public String getDescription() {
        return this.pizza.getDescription() + " + sausage";
    }

    @Override
    public BigDecimal getCost() {
        return (new BigDecimal(2.70)).add(this.pizza.getCost());
    }
}
