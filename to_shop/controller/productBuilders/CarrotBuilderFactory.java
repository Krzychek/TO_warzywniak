package to_shop.controller.productBuilders;

import to_shop.model.products.Carrot;

public class CarrotBuilderFactory {
    public CarrotBuilder getCarrotBuilder(Carrot.CarrotType type) {
        switch (type) {
            case GREEN: return new GreenCarrotBuilder();
            case YELLOW:return new YellowCarrotBuilder();
            default: return null;
        }
    }
}
