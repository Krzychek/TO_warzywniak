package to_shop.controller.productBuilders;

import to_shop.model.products.Banana;
import to_shop.model.products.Banana.BananaType;

class YellowBananaBuilder extends BananaBuilder {

	public YellowBananaBuilder() {}

	@Override
	public void createNewBanana() {
		banana = new Banana(BananaType.YELLOW);
	}

}
