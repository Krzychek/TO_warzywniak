package to_shop.controller.productBuilders;

import to_shop.model.products.Carrot;

public abstract class CarrotBuilder {

	protected Carrot carrot;
	
	public abstract void createNewCarrot();
	
	public void ripeApple() {
		carrot.ripe();
	}
}
