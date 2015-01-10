package to_shop.model.productBuilders;

import to_shop.model.products.Carrot;
import to_shop.model.products.Carrot.CarrotType;

public class YellowCarrotBuilder extends CarrotBuilder {

	public YellowCarrotBuilder() {}

	@Override
	public void createNewCarrot() {
		carrot = new Carrot(CarrotType.YELLOW);
	}

}