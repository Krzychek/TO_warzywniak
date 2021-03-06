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
	private List<Shop> shopList = new ArrayList<>();
	private ProductContainer container;
	private List<DetailedProduct> discountList;

	public Collection<DetailedProduct> getRWProductCollection() {
		return container.getRWProductCollection();
	}

	public static class NotEnoughAmountException extends Exception {
		public NotEnoughAmountException(int exp, int was) {
			super("expected: " + exp +"; was: " + was);
		}
	}

	public Shop() {
		this(new UniqueProductContainer());
		addObserver(EventHistory.getInstance());
	}

	public Shop(ProductContainer container) {
		shopList.add(this);
		this.container = container;
		discountList = new ArrayList<>();
	}
	
	public void addProduct(Product item, int amount, double price) {
		DetailedProduct detailedProduct = new ProductWrapper(item);
		detailedProduct.setPrice(price);
		detailedProduct.addAmount(amount);
		container.addProduct(detailedProduct);
	}

	@Override
	public String toString() {
		return "shop";
	}

	public Product sellProduct(Product item, int amount, Client client) throws NotEnoughAmountException, Client.NotEnoughMoneyException {
		DetailedProduct product = getDetailedProduct(item);
		if (amount > product.getAmount())
			throw new NotEnoughAmountException(amount, product.getAmount());
		client.takeMoney(amount * product.getPrice());
		setChanged();
		notifyObservers(new SellEvent(client, item, product.getPrice(), amount));

		product.addAmount(-amount);
		DetailedProduct result = product.clone();
		result.setAmount(amount);
		result.setPrice(Double.POSITIVE_INFINITY);
		return result;
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

	public void addProduct(Product product) {
		container.addProduct(product);
	}

	public void rmProduct(Product product) {
		container.rmProduct(product);
	}
}
