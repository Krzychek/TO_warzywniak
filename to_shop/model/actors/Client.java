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

	public Client(double money) {
		container = new UniqueProductContainer();
		this.money = money;
		addObserver(EventHistory.getInstance());
	}

	public double getMoney() {
		return money;
	}

	private double money = 0;


	public boolean buy(Shop shop, Product item, int amount) {
		if (money >= amount * shop.getPrice(item) && shop.sellProduct(item, amount, this)) {
			container.addProduct(item);
			money -= amount * shop.getPrice(item);
			setChanged();
			notifyObservers(new BuyEvent(shop, item, shop.getPrice(item), amount));
			return true;
		}
		return false;
	}

	public Collection<DetailedProduct> getProductCollection() {
		return container.getProductCollection();
	}

}
