package to_shop.model.products;

public abstract class Fruit extends Product implements Cloneable {
	boolean isRipe;
	
	protected Fruit(String name) {
		super(name);
		isRipe = false;
	}
	
	public void ripe() {
		isRipe = true;
	}
	
	@Override
	public String getName() {
		return (isRipe ? "ripe " : "unripe ") + super.getName();
	}

}
