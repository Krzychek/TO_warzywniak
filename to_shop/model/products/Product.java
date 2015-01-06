package to_shop.model.products;

public abstract class Product implements Cloneable {
	String name;
	
	protected Product(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name.toLowerCase();
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Product) {
			return ((Product) obj).getName() == this.name;
		} else {
			return false;
		}
	}

	@Override
	public Product clone() throws CloneNotSupportedException {
		return (Product) super.clone();
	}
}
