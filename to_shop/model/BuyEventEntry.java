package to_shop.model;

import to_shop.model.actors.Client;
import to_shop.model.products.Product;

import java.util.Date;

public class BuyEventEntry {
    private final Client client;
    private final Date date;
    private final Product item;
    private final int amount;
    private final double price;

    public BuyEventEntry(Client client,Product item, int amount, double price) {
        this.client = client;
        this.item = item;
        this.amount = amount;
        this.price = price;
        this.date = new Date();
    }

    public Date getDate() {
        return date;
    }

    public Product getItem() {
        return item;
    }

    public Client getClient() {
        return client;
    }

    public int getAmount() {
        return amount;
    }

    public double getPrice() {
        return price;
    }
}
