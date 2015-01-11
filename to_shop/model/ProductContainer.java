package to_shop.model;

import to_shop.model.products.DetailedProduct;
import to_shop.model.products.Product;

import java.util.Collection;

public interface ProductContainer {
	public void addProduct(Product item);

	public DetailedProduct getDetailedProduct(Product item);

	public <T> Collection<DetailedProduct> getProductCollection();

	public <T> Collection<DetailedProduct> getProductCollection(Class<T> clazz);

	public boolean rmProduct(Product item, int amount);
}