package to_shop.controller.productBuilders;

import to_shop.model.products.Banana;
import to_shop.model.products.Banana.BananaType;

class GreenBananaBuilder extends BananaBuilder {

	public GreenBananaBuilder() {}

	@Override
	public void createNewBanana() {
		banana = new Banana(BananaType.GREEN);
	}

}
