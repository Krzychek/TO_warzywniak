package to_shop.model.actors;

import to_shop.model.ProductContainer;
import to_shop.model.UniqueProductContainer;
import to_shop.model.actors.events.BuyEvent;
import to_shop.model.products.DetailedProduct;
import to_shop.model.products.Product;

import java.util.Collection;
import java.util.Observable;

public class Client extends Observable {
    private ProductContainer container;
    private double money;

    public Client() {
        this(200);
    }

    public Client(double money) {
        container = new UniqueProductContainer();
        this.money = money;
        addObserver(EventHistory.getInstance());
    }

    @Override
    public String toString() {
        return "client";
    }

    public void takeMoney(double price) throws NotEnoughMoneyException {
        if (money - price < 0)
            throw new NotEnoughMoneyException(money, price);
        money -= price;

    }

    public double getMoney() {
        return money;
    }

    public void buy(Shop shop, Product item, int amount) throws Shop.NotEnoughAmountException, NotEnoughMoneyException {
        container.addProduct(shop.sellProduct(item, amount, this));
        setChanged();
        notifyObservers(new BuyEvent(shop, item, shop.getPrice(item), amount));
    }

    public Collection<DetailedProduct> getProductCollection() {
        return container.getProductCollection();
    }

    public static class NotEnoughMoneyException extends Exception {
        public NotEnoughMoneyException(double exp, double was) {
            super("expected: " + exp + "; was: " + was);
        }
    }

}
