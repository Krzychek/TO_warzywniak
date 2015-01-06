package to_shop.model;

import to_shop.model.products.Product;
import to_shop.model.products.ProductWrapper;

import java.util.*;

public class UniversalProductContainer implements ProductContainer {
	private HashMap<Product, ProductWrapper> productList = new HashMap<>();

	@Override
	public void addProduct(ProductWrapper item) {
		if (!productList.containsKey(item.getSimpleProduct())) {
			productList.put(item.getSimpleProduct(), item);
		} else {
			productList.get(item.getSimpleProduct()) . addAmount(item.getAmount());
		}
	}

	@Override
	public void addProduct(Product item) {
		if (!productList.containsKey(item)) {
			if (item instanceof ProductWrapper) {
				productList.put(((ProductWrapper) item).getSimpleProduct(), (ProductWrapper) item);
			} else {
				productList.put(item, new ProductWrapper(item));
			}
		}
	}

	@Override
	public ProductWrapper getWrappedProduct(Product item) {
		return productList.get(item);
	}

	@Override
	public int getAmountOf(Product item) {
		if (productList.containsKey(item))
			return productList.get(item).getAmount();
		return 0;
	}

	@Override
	public double getPriceOf(Product item) {
		if (productList.containsKey(item))
			return productList.get(item).getPrice();
		return 0;
	}

	@Override
	public boolean rmProduct(Product item, int amount) {
		ProductWrapper productWrapper = productList.get(item);
		if (productWrapper != null) {
			if (productWrapper.getAmount() >= amount) {
				productWrapper.addAmount(-amount);
				return true;
			}
		}
		return false;
	}
	
	@Override
	public String toString() {
		StringBuilder strBuilder = new StringBuilder();

		strBuilder.append("Container: [");
		for (Product product : this.productList.values()) {
			strBuilder.append(product.toString());
			strBuilder.append(", ");
		}
		strBuilder.append("]");

		return strBuilder.toString();
	}

	@Override
	public Collection<Product> getProductCollection() {
		return Collections.unmodifiableCollection(productList.values());
	}

	@Override
	public <T> Collection<Product> getProductCollection(Class<T> clazz) {
		List<T> result = new ArrayList<T>();
		for (ProductWrapper item : productList.values()) {
			if(clazz.isInstance(item)) {
				result.add((T) item);
			}
		}
		return null;
	}
}
