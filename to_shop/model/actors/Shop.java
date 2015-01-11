package to_shop.model.actors;

import to_shop.model.ProductContainer;
import to_shop.model.UniqueProductContainer;
import to_shop.model.actors.events.SellEvent;
import to_shop.model.products.DetailedProduct;
import to_shop.model.products.Product;
import to_shop.model.products.ProductWrapper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Observable;

public class Shop extends Observable {
	private List<SellEvent> historyList = new ArrayList<>();
	private List<Shop> shopList = new ArrayList<>();
	private ProductContainer container;
	private List<DetailedProduct> discountList;

	public Shop() {
		this(new UniqueProductContainer());
		addObserver(EventHistory.getInstance());
	}

	public Shop(ProductContainer container) {
		shopList.add(this);
		this.container = container;
		discountList = new ArrayList<>();
	}
	
	public void setPrice(Product item, double price) {
		container.getDetailedProduct(item).setPrice(price);
	}
	
	public void addProduct(Product item, int amount, double price) {
		DetailedProduct detailedProduct = new ProductWrapper(item);
		detailedProduct.setPrice(price);
		detailedProduct.addAmount(amount);
		container.addProduct(detailedProduct);
	}

	public boolean sellProduct(Product item, int amount, Client client) {
		if(container.rmProduct(item, amount)) {
			setChanged();
			notifyObservers(new SellEvent(client, item, getPrice(item), amount));
			return true;
		}
		return false;
	}

	public void addProductToDiscountList(Product item) {
		discountList.add(getDetailedProduct(item));
	}

	public void setDiscount(double discount) {
		discountList.forEach((item) -> item.setDiscount(discount));
	}

	public DetailedProduct getDetailedProduct (Product item) {
		return container.getDetailedProduct(item);
	}

	public Collection<DetailedProduct> getProductCollection() {
		return container.getProductCollection();
	}

	public Collection<DetailedProduct> getProductCollection(Class<Product> clazz) {
		return container.getProductCollection(clazz);
	}

	public void clearShop() {
		container = new UniqueProductContainer();
	}

	public double getPrice(Product item) {
		return getDetailedProduct(item).getPrice();
	}
}
