package to_shop.model;

import to_shop.model.products.Product;
import to_shop.model.products.ProductWrapper;

import java.util.Collection;

public interface ProductContainer {

	public void addProduct(ProductWrapper item);

	void addProduct(Product item);

	ProductWrapper getWrappedProduct(Product item);

	public int getAmountOf(Product item);

	public double getPriceOf(Product item);

	public <T> Collection<Product> getProductCollection();

	public <T> Collection<Product> getProductCollection(Class<T> clazz);

	public boolean rmProduct(Product item, int amount);
}