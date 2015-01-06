package to_shop.controller.productBuilders;

import to_shop.model.products.Banana;
public abstract class BananaBuilder {
	
	protected Banana banana;

	public abstract void createNewBanana();
	
	public void ripeApple() {
		banana.ripe();
	}
}
