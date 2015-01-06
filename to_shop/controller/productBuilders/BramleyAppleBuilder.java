package to_shop.controller.productBuilders;

import to_shop.model.products.Apple;
import to_shop.model.products.Apple.AppleType;

class BramleyAppleBuilder extends AppleBuilder {
	
	public BramleyAppleBuilder() {}

	@Override
	public void createNewApple() {
		apple = new Apple(AppleType.BRAMEY);
	}

}
