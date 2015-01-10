package to_shop.model.productBuilders;

import to_shop.model.products.Apple;
public abstract class AppleBuilder {
	
	protected Apple apple;

	public abstract void createNewApple();
	
	public void ripeApple() {
		apple.ripe();
	}
}
