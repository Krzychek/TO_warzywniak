package to_shop.model.actors;

public class MainShop extends Shop {

	private static MainShop instance;
	
	public static MainShop getInstance() {
		if (instance == null) {
			instance = new MainShop();
		}
		return instance;
	}
	
	private MainShop() {
		super();
	}
}
