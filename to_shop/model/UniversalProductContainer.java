package to_shop.model;

import to_shop.model.products.Product;
import to_shop.model.products.Properties;

import java.util.*;

public class UniversalProductContainer implements ProductContainer {
	private HashMap<Product, HashMap<Properties, Object>> productList =
			new HashMap<Product, HashMap<Properties, Object>>();

	@Override
	public void addProduct(Product item) {
		if (!this.productList.containsKey(item)) {
			this.productList.put(item, new HashMap<Properties, Object>());
		}
	}

	@Override
	public void addProduct(Product item, int amount) {
		this.addProduct(item);
		if (this.productList.get(item).containsKey(Properties.AMOUNT)) {
			this.productList.get(item).put(
					Properties.AMOUNT,
					(Integer) this.productList.get(item).get(Properties.AMOUNT)
							+ amount);
		} else {
			this.productList.get(item).put(Properties.AMOUNT, amount);
		}
	}

	@Override
	public int getAmountOf(Product item) {
		if (this.productList.containsKey(item) && this.productList.get(item).containsKey(Properties.AMOUNT) ) {
			return (Integer) this.productList.get(item).get(Properties.AMOUNT);
		}
		return 0;
	}
	
	
	
	@Override
	public boolean rmProduct(Product item, int amount) {
		if (this.productList.containsKey(item)) {
			int tmpAmount = (Integer)this.productList.get(item).get(Properties.AMOUNT);
			if (tmpAmount <= amount) {
				this.productList.get(item).put(Properties.AMOUNT, tmpAmount - amount);
				return true;
			}
		}
		return false;
	}

	@Override
	public void setProp (Product item, Properties property, double value) {
		this.addProduct(item);
		this.productList.get(item).put(property, value);
	}

	@Override
	public Object getProp (Product item, Properties property) {
		if(productList.containsKey(item))
			return this.productList.get(item).get(property);
		else
			return null;
	}
	
	@Override
	public String toString() {
		StringBuilder strBuilder = new StringBuilder();

		strBuilder.append("Container: [");
		for (Product product : this.productList.keySet()) {
			strBuilder.append(product.toString());
			strBuilder.append(", ");
		}
		strBuilder.append("]");

		return strBuilder.toString();
	}

	@Override
	public Collection<Product> getProductCollection() {
		return Collections.unmodifiableCollection(productList.keySet());
	}

	@Override
	public <T> Collection<Product> getProductCollection(Class<T> clazz) {
		List<T> result = new ArrayList<T>();
		for (Product item : productList.keySet()) {
			if(clazz.isInstance(item)) {
				result.add((T) item);
			}
		}
		return null;
	}
}
