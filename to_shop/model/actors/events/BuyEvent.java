package to_shop.model.actors.events;

import to_shop.model.actors.Shop;
import to_shop.model.products.DetailedProduct;
import to_shop.model.products.Product;
import to_shop.model.products.ROProductWrapper;

import java.util.Date;

public class BuyEvent extends Event {
    private final Shop shop;
    private final Date date;
    private final DetailedProduct item;
    private final int amount;

    public BuyEvent(Shop shop, Product item, double price, int amount) {
        this.shop = shop;
        this.item = new ROProductWrapper(item, price, amount);
        this.amount = amount;
        this.date = new Date();
    }

    @Override
    public Date getDate() {
        return date;
    }

    @Override
    public DetailedProduct getItem() {
        return item;
    }

    public Shop getShop() {
        return shop;
    }

}
