package to_shop.model.products;

public class Banana extends Fruit implements Cloneable {
	
	public static enum BananaType { YELLOW, GREEN}
	BananaType type;
	
	public Banana(BananaType type) {
		super(type.name() + " banana");
		this.type = type;
	}
	
	public BananaType getType() { return type; }

}
