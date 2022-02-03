package com.infoworks.lab.dpatterns.decorator.mypizza;

import java.math.BigDecimal;

/**
 * This is the component we love to decorate on the fly.
 */
public interface Pizza {
    String getDescription();
    BigDecimal getCost();
    default float convertCost() { return Float.valueOf(getCost().toPlainString()); }
    default String printedCost() { return String.format("%.2f", convertCost()); }
}
