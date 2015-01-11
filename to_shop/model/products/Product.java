package to_shop.model.products;

public abstract class Product implements Cloneable {
	String name;

	Product() {}
	
	protected Product(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name.toLowerCase();
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof DetailedProduct
				&& ((DetailedProduct) obj).getProduct().equals(this)) {
			return true;
		} else if (obj instanceof Product)
			return ((Product) obj).getName() == this.toString();
		return false;
	}

	@Override
	public Product clone()  {
		try {
			return (Product) super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
			return null;
		}
	}

	public Product unPack() {
		if (this instanceof DetailedProduct) {
			return ((DetailedProduct) this).getProduct().unPack();
		}
		return this;
	}
}
