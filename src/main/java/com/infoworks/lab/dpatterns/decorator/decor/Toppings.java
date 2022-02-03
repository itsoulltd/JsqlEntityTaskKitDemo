package com.infoworks.lab.dpatterns.decorator.decor;

import com.infoworks.lab.dpatterns.decorator.mypizza.Pizza;

/**
 * This is our interface for decorating our pizza toppings.
 */
public interface Toppings extends Pizza {
    void setPizza(Pizza pizza);
}
