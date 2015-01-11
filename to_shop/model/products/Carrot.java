package to_shop.model.products;

public class Carrot extends Veggie implements Cloneable {

	public static enum CarrotType { YELLOW, GREEN}
	CarrotType type;
	
	public Carrot(CarrotType type) {
		super(type.name() + " carrot");
		this.type = type;
	}

	public CarrotType getType() {
		return type;
	}

}
