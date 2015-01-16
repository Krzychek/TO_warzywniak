package to_shop.model;

import to_shop.model.products.DetailedProduct;
import to_shop.model.products.Product;
import to_shop.model.products.ProductWrapper;
import to_shop.model.products.ROProductWrapper;

import java.util.Collection;
import java.util.HashMap;
import java.util.stream.Collectors;

public class UniqueProductContainer implements ProductContainer {
	private HashMap<Product, DetailedProduct> productList = new HashMap<>();

	@Override
	public void addProduct(Product item) {
		if (!productList.containsKey(item.unPack())) {
			if (item instanceof DetailedProduct)
				productList.put(item.unPack(), (DetailedProduct) item);
			else
				productList.put(item, new ProductWrapper(item));
		}
	}

	@Override
	public DetailedProduct getDetailedProduct(Product item) {
		return productList.get(item.unPack());
	}

	@Override
	public void rmProduct(Product item) {
		productList.remove(item.unPack());
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
	public Collection<DetailedProduct> getRWProductCollection() {
		return productList.values();
	}

	@Override
	public Collection<DetailedProduct> getProductCollection() {
		return productList.entrySet().stream().map(entry -> new ROProductWrapper(entry.getValue()))
				.collect(Collectors.toList());
	}

	@Override
	public <T> Collection<DetailedProduct> getProductCollection(Class<T> clazz) {
		return productList.entrySet().stream().filter(entry -> clazz.isInstance(entry.getKey()))
				.map(entry -> new ROProductWrapper(entry.getValue())).collect(Collectors.toList());
	}
}
