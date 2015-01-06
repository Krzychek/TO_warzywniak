package to_shop.controller.actors;

import to_shop.model.BuyEventEntry;
import to_shop.model.ProductContainer;
import to_shop.model.UniversalProductContainer;
import to_shop.model.products.Product;
import to_shop.model.products.Properties;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static to_shop.model.products.Properties.PRICE;

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
		container.addProduct(item);
		container.setProp(item, PRICE, price);
	}

	public double getPrice(Product item) {
		if (container.getProp(item, PRICE) != null)
			return (Double) container.getProp(item, Properties.PRICE);
		else
			return Double.POSITIVE_INFINITY;
	}
	
	public void addProduct(Product item, int amount, double price) {
		container.addProduct(item, amount);
		this.setPrice(item, price);
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
