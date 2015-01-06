package to_shop.controller.productBuilders;

import to_shop.model.products.Apple;
import to_shop.model.products.Apple.AppleType;


class FujiAppleBuilder extends AppleBuilder {
	
	public FujiAppleBuilder() {}

	@Override
	public void createNewApple() {
		apple = new Apple(AppleType.FUJI);
	}


}
