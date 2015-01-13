package to_shop.model.products;

public abstract class Veggie extends Product implements Cloneable {
	protected Veggie(String name) {
		super(name);
	}

	@Override
	public String getCategory() {
		return "Veggie";
	}
}
