package to_shop.model.actors.events;

import to_shop.model.actors.Client;
import to_shop.model.products.DetailedProduct;
import to_shop.model.products.Product;
import to_shop.model.products.ROProductWrapper;

import java.util.Date;

public class SellEvent extends Event {
    private final Client client;
    private final Date date;
    private final DetailedProduct item;
    private final int amount;

    public SellEvent(Client client, Product item, double price, int amount) {
        this.client = client;
        this.item = new ROProductWrapper(item, price, amount);
        this.amount = amount;
        this.date = new Date();
    }

    @Override
    public Date getDate() {
        return date;
    }

    public DetailedProduct getItem() {
        return item;
    }

    public Client getClient() {
        return client;
    }

    public int getAmount() {
        return amount;
    }

}
