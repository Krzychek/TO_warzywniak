package to_shop.model;

import to_shop.model.products.Product;
import to_shop.model.products.Properties;

import java.util.Collection;

public interface ProductContainer {

	public void addProduct(Product item);

	public void addProduct(Product item, int amount);

	public int getAmountOf(Product item);

	public <T> Collection<Product> getProductCollection();
	
	public <T> Collection<Product> getProductCollection(Class<T> clazz);

	public boolean rmProduct(Product item, int amount);

	public void setProp(Product item, Properties property, double value);

	public Object getProp(Product item, Properties property);
}