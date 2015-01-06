package to_shop.controller.productBuilders;

import to_shop.model.products.Apple;
public abstract class AppleBuilder {
	
	protected Apple apple;

	public abstract void createNewApple();
	
	public void ripeApple() {
		apple.ripe();
	}
}
