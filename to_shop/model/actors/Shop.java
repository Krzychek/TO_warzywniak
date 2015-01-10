package to_shop.model.actors;

import to_shop.model.BuyEventEntry;
import to_shop.model.ProductContainer;
import to_shop.model.UniversalProductContainer;
import to_shop.model.products.Product;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Shop {
	private List<BuyEventEntry> historyList = new ArrayList<>();
	private List<Shop> shopList = new ArrayList<Shop>();
	private ProductContainer container;

	public Shop() {
		shopList.add(this);
		this.container = new UniversalProductContainer();
	}

	public Shop(ProductContainer container) {
		shopList.add(this);
		this.container = container;
	}
	
	public void setPrice(Product item, double price) {
		// TODO
	}

	public double getPrice(Product item) {
		return container.getPriceOf(item);
	}
	
	public void addProduct(Product item, int amount, double price) {
		// TODO
	}

	public boolean sellProduct(Product item, int amount, Client client) {
		if(container.rmProduct(item, amount)) {
			return historyList.add(new BuyEventEntry(client,item,amount,getPrice(item)));
		} else {
			return false;
		}
	}

	public Collection<Product> getProductCollection() {
		return container.getProductCollection();
	}

	public Collection<Product> getProductCollection(Class<Product> clazz) {
		return container.getProductCollection(clazz);
	}

	public void clearShop() {
		container = new UniversalProductContainer();
	}
}
