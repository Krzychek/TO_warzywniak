package to_shop.model.actors.events;

import to_shop.model.products.DetailedProduct;

import java.util.Date;

public abstract class Event {
    public abstract Date getDate();

    public abstract DetailedProduct getItem();

    @Override
    public abstract String toString();
}
