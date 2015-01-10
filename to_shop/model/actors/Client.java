package to_shop.model.actors;

import to_shop.model.ProductContainer;
import to_shop.model.UniversalProductContainer;
import to_shop.model.products.Product;

import java.util.Collection;

public class Client {
	private ProductContainer container = new UniversalProductContainer();

	public int getMoney() {
		return money;
	}

	private int money = 0;

	public boolean buy(Shop shop, Product item, int amount) {
		if (money >= amount * shop.getPrice(item) && shop.sellProduct(item, amount, this)) {
			container.addProduct(item, amount);
			money -= amount * shop.getPrice(item);
			return true;
		}
		return false;
	}


	public Client(int money) {
		this.money = money;
	}

	public Collection<Product> getProductCollection() {
		return container.getProductCollection();
	}

}
