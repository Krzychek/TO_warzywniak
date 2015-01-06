package to_shop.controller.productBuilders;

import to_shop.model.products.Carrot;
import to_shop.model.products.Carrot.CarrotType;

class GreenCarrotBuilder extends CarrotBuilder {

	public GreenCarrotBuilder() {}

	@Override
	public void createNewCarrot() {
		carrot = new Carrot(CarrotType.GREEN);
	}

}
