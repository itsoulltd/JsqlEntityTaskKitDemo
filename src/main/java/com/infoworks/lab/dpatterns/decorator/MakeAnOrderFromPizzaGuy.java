package com.infoworks.lab.dpatterns.decorator;

import com.infoworks.lab.dpatterns.decorator.decor.Cheese;
import com.infoworks.lab.dpatterns.decorator.decor.Mushroom;
import com.infoworks.lab.dpatterns.decorator.decor.Pepperoni;
import com.infoworks.lab.dpatterns.decorator.decor.Sausage;
import com.infoworks.lab.dpatterns.decorator.mypizza.Pizza;
import com.infoworks.lab.dpatterns.decorator.mypizza.ThickCrustPizza;

public class MakeAnOrderFromPizzaGuy {

    public static void main (String...args){
        //Build my Pizza
        Pizza myPizza = new ThickCrustPizza();
        //add cheese
        Cheese cheese = new Cheese(myPizza);
        System.out.println(String.format("%s (will cost): %s", cheese.getDescription(), cheese.printedCost()));
        //add sausage
        Sausage sausage = new Sausage(cheese);
        System.out.println(String.format("%s (will cost): %s", sausage.getDescription(), sausage.printedCost()));
        //add pepperoni
        Pepperoni pepperoni = new Pepperoni(sausage);
        System.out.println(String.format("%s (will cost): %s", pepperoni.getDescription(), pepperoni.printedCost()));
        //add mushroom
        Mushroom mushroom = new Mushroom(pepperoni);
        System.out.println(String.format("%s (will cost): %s", mushroom.getDescription(), mushroom.printedCost()));
    }

}
