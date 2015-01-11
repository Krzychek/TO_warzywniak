package to_shop.model.actors.events;

import to_shop.model.products.DetailedProduct;

import java.util.Date;

/**
 * Created by krzysiek on 11.01.15.
 */
public abstract class Event {
    public abstract Date getDate();

    public abstract DetailedProduct getItem();
}
